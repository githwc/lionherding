package com.lh.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lh.common.config.exception.RunException.RunningException;
import com.lh.common.config.filter.JwtUtil;
import com.lh.common.constant.CacheConstant;
import com.lh.common.constant.CommonConstant;
import com.lh.common.dao.DaoApi;
import com.lh.common.utils.EncoderUtil;
import com.lh.common.utils.IdcardUtils;
import com.lh.common.utils.RandomUtils;
import com.lh.common.utils.RedisUtil;
import com.lh.system.entity.SysUser;
import com.lh.system.entity.SysUserRole;
import com.lh.system.mapper.SysUserMapper;
import com.lh.system.mapper.SysUserRoleMapper;
import com.lh.system.model.query.UserQuery;
import com.lh.system.model.vo.SysUserVO;
import com.lh.system.service.SysLogService;
import com.lh.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2019-09-19
 * @Version: 1.0.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private RedisUtil redisUtil;
    private SysLogService sysLogService;
    private final SysUserRoleMapper sysUserRoleMapper;
    private final DaoApi daoApi;

    @Autowired
    public SysUserServiceImpl(DaoApi daoApi,SysUserRoleMapper sysUserRoleMapper,RedisUtil redisUtil,SysLogService sysLogService) {
        this.daoApi = daoApi;
        this.sysUserRoleMapper = sysUserRoleMapper;
        this.redisUtil = redisUtil;
        this.sysLogService = sysLogService;
    }

    @Override
    public JSONObject login(SysUser sysUser) {
        String loginName = sysUser.getLoginName();
        String password = sysUser.getPassword();
        sysUser = this.getUserByName(loginName);
        if (sysUser == null) {
            sysLogService.addLog("登录失败，用户名:" + loginName + "不存在！", CommonConstant.LOG_TYPE_1, "sysUser/login", "loginName:" + loginName + ",password:" + password);
            throw new RunningException("该用户不存在！");
        } else {
            // 是否冻结
            if(sysUser.getDelFlag() == CommonConstant.DEL_FLAG_1){
                sysLogService.addLog("登录失败，用户名:" + loginName + "已被冻结！", CommonConstant.LOG_TYPE_1, "sysUser/login", "loginName:" + loginName + ",password:" + password);
                throw new RunningException("账号已被锁定,请联系管理员！");
            }
            // 密码验证
            String requestPassword = EncoderUtil.encrypt(loginName, password, sysUser.getSalt());
            String sysPassword = sysUser.getPassword();
            if(!sysPassword.equals(requestPassword)) {
                sysLogService.addLog("登录失败，用户:"+loginName+"密码输入错误！", CommonConstant.LOG_TYPE_1, "sysUser/login","loginName:"+loginName+",password:"+password);
                throw new RunningException("密码错误,请重新输入！");
            }
            JSONObject jsonObject = new JSONObject();
            // 生成token
            String token = JwtUtil.sign(loginName, sysPassword);
            //设置缓存token
            redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, token);
            // 设置超时时间
            redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME*2 / 1000);
            jsonObject.put("token", token);
            jsonObject.put("userInfo", sysUser);
            // 记录登录数据
            this.dealUser(sysUser);
            sysLogService.addLog("用户名: " + loginName + ",登录成功！", CommonConstant.LOG_TYPE_1, "sysUser/login", "loginName:" + loginName + ",password:" + password);
            return jsonObject;
        }
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Subject subject = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser)subject.getPrincipal();
        sysLogService.addLog("用户名: "+sysUser.getLoginName()+",退出成功！", CommonConstant.LOG_TYPE_1, "sysUser/logout","");
        subject.logout();

        String token = request.getHeader(CommonConstant.X_ACCESS_TOKEN);
        //清空用户Token缓存
        redisUtil.del(CommonConstant.PREFIX_USER_TOKEN + token);
        //清空用户权限缓存：权限Perms和角色集合
        redisUtil.del(CommonConstant.LOGIN_USER_CACHERULES_ROLE + sysUser.getLoginName());
        redisUtil.del(CommonConstant.LOGIN_USER_CACHERULES_PERMISSION + sysUser.getLoginName());
    }

    @Override
    public SysUser getUserByName(String loginName) {
        return this.baseMapper.selectOne(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getLoginName, loginName)
        );
    }

    @Override
    public void dealUser(SysUser sysUser) {
        sysUser.setLoginCount(sysUser.getLoginCount() + 1);
        if (ObjectUtil.isNotNull(sysUser.getLastLoginTime())) {
            if (!sysUser.getLastLoginTime().toString().substring(0, 10)
                    .equalsIgnoreCase(LocalDateTime.now().toString().substring(0, 10))) {// 判断上一次登录时间是当前时间的前一天或更多天则证明今天没登陆过
                sysUser.setTodayLoginCount(1);
            } else {
                sysUser.setTodayLoginCount(sysUser.getTodayLoginCount() + 1);
            }
        } else {
            sysUser.setTodayLoginCount(1);
        }
        if (ObjectUtil.isNotNull(sysUser.getFirstLoginTime())) {
            sysUser.setFirstLoginTime(LocalDateTime.now());
        }
        sysUser.setLastLoginTime(LocalDateTime.now());
        this.baseMapper.updateById(sysUser);
    }

    @Override
    public Page<SysUserVO> userList(Page<SysUserVO> page, UserQuery userQuery) {
        return this.baseMapper.userList(page,userQuery);
    }

    @Override
    public void add(JSONObject jsonObject) {
        SysUser user = JSON.parseObject(jsonObject.toJSONString(), SysUser.class);
        String salt = RandomUtils.getRandomNumbersAndLetters(8);
        user.setSalt(salt);
        String passwordEncode = EncoderUtil.encrypt(user.getLoginName(), "123456", salt);
        user.setPassword(passwordEncode);
        user.setCreateUserId(daoApi.getCurrentUserId());
        user.setAge(IdcardUtils.getAgeByIdCard(user.getIdCard()));
        user.setSex(IdcardUtils.getSexByIdCard(user.getIdCard()));
        user.setBirthday(LocalDate.parse(IdcardUtils.getBirthByIdCard(user.getIdCard())));
        this.save(user);
        String roles = jsonObject.getString("selectedroles");
        if(StringUtils.isNotEmpty(roles)) {
            String[] arr = roles.split(",");
            for (String roleId : arr) {
                SysUserRole userRole = new SysUserRole(user.getSysUserId(), roleId);
                sysUserRoleMapper.insert(userRole);
            }
        }
    }

    @Override
    public void edit(JSONObject jsonObject) throws RunningException{
        SysUser sysUser = this.getById(jsonObject.getString("sysUserId"));
        if(sysUser==null) {
            throw new RunningException("未找到对应实体");
        }else {
            SysUser user = JSON.parseObject(jsonObject.toJSONString(), SysUser.class);
            user.setPassword(sysUser.getPassword());
            String roles = jsonObject.getString("selectedroles");
            this.updateById(user);
            // 角色先删后加
            sysUserRoleMapper.delete(new LambdaQueryWrapper<SysUserRole>()
                    .eq(SysUserRole::getUserId, user.getSysUserId()));
            if(StringUtils.isNotEmpty(roles)) {
                String[] arr = roles.split(",");
                for (String roleId : arr) {
                    SysUserRole userRole = new SysUserRole(user.getSysUserId(), roleId);
                    sysUserRoleMapper.insert(userRole);
                }
            }
        }
    }

    @Override
    public void checkIsOnly(String loginName) {
        List<SysUser> list = this.baseMapper.selectList(new LambdaQueryWrapper<SysUser>()
            .eq(SysUser::getLoginName,loginName)
        );
        if(list!= null && list.size()>0){
            throw new RunningException("该账号已存在！");
        }
    }

    @Override
    @CacheEvict(value={CacheConstant.SYS_USERS_CACHE}, allEntries=true)
    public void deleteUser(String id) {
        // 删除用户角色关联关系
        sysUserRoleMapper.delete(new LambdaQueryWrapper<SysUserRole>()
            .eq(SysUserRole::getUserId,id)
        );
        //删除用户
        SysUser user = new SysUser();
        user.setSysUserId(id);
        user.setDelFlag(CommonConstant.DEL_FLAG_1);
        this.baseMapper.updateById(user);
    }

    @Override
    public void deleteBatch(String ids) {
        String[] arr = ids.split(",");
        for (String id : arr) {
            if (StringUtils.isNotEmpty(id)) {
                this.deleteUser(id);
            }
        }
    }

    @Override
    public List<String> queryUserRole(String userId) {
        List<String> list = new ArrayList<String>();
        List<SysUserRole> userRole = sysUserRoleMapper.selectList(new LambdaQueryWrapper<SysUserRole>()
                .eq(SysUserRole::getUserId, userId));
        if (ObjectUtil.isNull(userRole)) {
            throw new RunningException("未找到用户相关角色信息");
        } else {
            for (SysUserRole sysUserRole : userRole) {
                list.add(sysUserRole.getRoleId());
            }
        }
        return list;
    }
}
