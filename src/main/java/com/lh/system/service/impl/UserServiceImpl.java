package com.lh.system.service.impl;

import com.lh.system.basis.Result;
import com.lh.system.basis.ResultCode;
import com.lh.system.dao.DaoApi;
import com.lh.system.log.SystemLogService;
import com.lh.system.log.WriteLog;
import com.lh.system.mapper.UserMapper;
import com.lh.system.entity.User;
import com.lh.system.service.UserService;
import org.framework.core.encoder.MD5;
import org.framework.core.utils.LocalHostUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 功能描述：
 * <p>
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author: 牧狮&&紫色年华
 * @Datetime: 2019-05-31 17:38
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DaoApi daoApi;

    /**
     * @Description:获取用户信息
     * @Date: 2019/6/13 15:13
     * @Param:
     * @Return:
     * @throws:
     */
    @Override
    public User getUserInfo(String loginName) {
        return userMapper.findByField(loginName);
    }

    /**
     * @Description:用户自行注册
     * @Date: 2019/6/13 14:05
     * @Param:
     * @Return:
     * @throws:
     */
    @Override
    @WriteLog(mName = "注册用户",optype = SystemLogService.OPTYPE_CREATE)
    public int register(User user) {
        //判断登录名一致性
        //对密码进行MD5加盐加密
        user.setPassword(MD5.getSaltMD5(user.getPassword()));
        // user.setPassword(MD5.encrypt(user.getPassword()));
        user.setId(daoApi.getUUID());
        user.setCreateDate(new Date());
        user.setIpAddress(LocalHostUtil.getIpAddress());
        user.setRegDate(new Date());
        user.setState(0);//默认未启用
        user.setSort(100);
        return userMapper.insert(user);
    }

    /**
     * @Description:用户登录
     * @Date: 2019/6/13 16:26
     * @Param:
     * @Return:
     * @throws:
     */
    @Override
    @WriteLog(mName = "登录用户",optype = SystemLogService.OPTYPE_READ)
    public Result<Object> login(String loginName, String password, HttpServletRequest request, HttpServletResponse response, String code, Model model) {
        //验证图片验证码
        if(!code.equalsIgnoreCase(request.getSession().getAttribute("code").toString())){//图片验证码与session中的验证码不一致
          return Result.fail(ResultCode.USER_PICTURE_VALIDATE);
        }
        User user = userMapper.findByField(loginName);//查询用户
        if(user!=null){
            //判断用户密码是否一致
            boolean result = MD5.compareStrAndSaltMD5(password,user.getPassword());
            if(result){//保存session
                HttpSession session = request.getSession();
                session.setAttribute(User.SESSION_CURRENT_USER, user);
                return Result.success(user);
            }else{
                return Result.fail(ResultCode.USER_LOGIN_ERROR);
            }
        }else{
            return Result.fail(ResultCode.USER_LOGIN_ERROR);
        }
    }

    @Override
    public List<User> userList() {
        return userMapper.userList();
    }
}
