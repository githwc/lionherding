package com.lh.system.service.impl;

import com.lh.system.dao.DaoApi;
import com.lh.system.mapper.UserMapper;
import com.lh.system.model.User;
import com.lh.system.service.UserService;
import org.framework.core.encoder.MD5;
import org.framework.core.utils.LocalHostUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

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
    public String login(String loginName, String password, HttpServletRequest request, HttpServletResponse response) {
        User user = userMapper.findByField(loginName);//查询用户
        if(user!=null){
            //判断用户密码是否一致
            boolean result = MD5.compareStrAndSaltMD5(password,user.getPassword());
            if(result){//保存session
                HttpSession session = request.getSession();
                session.setAttribute("user",user);
                session.setAttribute(User.SESSION_CURRENT_USER, user);
                System.out.println("当前类:UserServiceImpl.login()==="  );
                return "redirect:/main.html";
            }else{
                return "密码输入错误，请核对信息！";
            }
        }else{
            return "用户不存在，请核对信息！";
        }
    }
}
