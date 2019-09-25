package com.lh.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.lh.common.config.exception.userException.UserLoginNameException;
import com.lh.common.config.exception.userException.UserPasswordException;
import com.lh.common.constant.CommonConstant;
import com.lh.common.utils.EncoderUtil;
import com.lh.common.utils.JwtUtil;
import com.lh.common.utils.RedisUtil;
import com.lh.system.entity.SysUser;
import com.lh.system.service.SysLogService;
import com.lh.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * 功能描述：系统用户 Controller
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2019-09-19
 * @Version: 1.0.0
 *
 */
@RestController
@RequestMapping("/sysUser")
@Slf4j
@Api(tags="系统用户")
public class SysUserController {

    @Autowired
    public SysUserService iSysUserService;

    @Autowired
    public SysLogService sysLogService;

    @PostMapping("/login")
    @ApiOperation(value = "用户登录",notes = "用户登录")
    public JSONObject login(@RequestBody SysUser sysUser){
        String username = sysUser.getLoginName();
        String password = sysUser.getPassword();
        sysUser = iSysUserService.getUserByName(username);
        if(sysUser==null) {
            sysLogService.addLog("登录失败，用户名:"+username+"不存在！", CommonConstant.LOG_TYPE_1, "sysUser/login","loginName:"+username+",password:"+password);
            throw new UserLoginNameException("该用户不存在！");
        }else {
            // 密码验证
            String userpassword = EncoderUtil.encrypt(username, password, sysUser.getLoginName());
            String syspassword = sysUser.getPassword();
            if(!syspassword.equals(userpassword)) {
                sysLogService.addLog("登录失败，用户:"+username+"密码输入错误！", CommonConstant.LOG_TYPE_1, "sysUser/login","loginName:"+username+",password:"+password);
                throw new UserPasswordException("密码错误！");
            }
            JSONObject jsonObject = new JSONObject();
            // 生成token
            String token = JwtUtil.sign(username, syspassword);
            RedisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, token);
            // 设置超时时间
            RedisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME/1000);
            jsonObject.put("token", token);
            jsonObject.put("userInfo", sysUser);
            iSysUserService.dealUser(sysUser);  // 记录登录数据
            sysLogService.addLog("用户名: "+username+",登录成功！", CommonConstant.LOG_TYPE_1, "sysUser/login","loginName:"+username+",password:"+password);
            return jsonObject;
        }
    }

    /**
     * 退出登录
     * @return
     */
    @GetMapping(value = "/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        //用户退出逻辑
        Subject subject = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser)subject.getPrincipal();
        sysLogService.addLog("用户名: "+sysUser.getLoginName()+",退出成功！", CommonConstant.LOG_TYPE_1, "sysUser/logout","");
        subject.logout();

        String token = request.getHeader(CommonConstant.X_ACCESS_TOKEN);
        //清空用户Token缓存
        RedisUtil.del(CommonConstant.PREFIX_USER_TOKEN + token);
        //清空用户权限缓存：权限Perms和角色集合
        RedisUtil.del(CommonConstant.LOGIN_USER_CACHERULES_ROLE + sysUser.getLoginName());
        RedisUtil.del(CommonConstant.LOGIN_USER_CACHERULES_PERMISSION + sysUser.getLoginName());
    }



}
