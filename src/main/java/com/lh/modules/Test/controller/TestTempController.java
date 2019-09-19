package com.lh.modules.Test.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 功能描述：
 *
 * <p>版权所有：</p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author:   xie
 * @Datetime: 2019-09-18 14:01
 * @Version: 1.0.0
 */
@Controller
public class TestTempController {


    @RequestMapping("/test")
    public String test(){
        return "test";
    }

    @RequestMapping("/add")
    public String add(){
        return "/user/add";
    }

    @RequestMapping("/update")
    public String update(){
        return "/user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/noAuth")
    public String noAuth(){
        return "noAuth";
    }

    @RequestMapping("/login")
    public String login(String name, String password, Model model){
        // 获取Subject
        Subject subject = SecurityUtils.getSubject();
        // 封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name,password);
        // 执行登录方法
        try{
            subject.login(token);   // 调用自定义的shiroRealm 执行登录认证
            return "test";
        }catch(UnknownAccountException e){
            model.addAttribute("msg","登录失败,用户名错误！");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","登录失败,密码错误！");
            return "login";
        }
    }

}
