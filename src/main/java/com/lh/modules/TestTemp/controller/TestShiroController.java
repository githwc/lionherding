package com.lh.modules.TestTemp.controller;

import com.alibaba.fastjson.JSONObject;
import com.lh.common.config.exception.userException.RunningException;
import com.lh.common.config.filter.JwtUtil;
import com.lh.common.constant.CommonConstant;
import com.lh.common.log.WriteLog;
import com.lh.common.utils.EncoderUtil;
import com.lh.common.utils.RedisUtil;
import com.lh.system.entity.SysUser;
import com.lh.system.service.SysLogService;
import com.lh.system.service.SysUserService;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 功能描述：
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author:   xieyc
 * @Datetime: 2019-09-18 14:01
 * @Version: 1.0.0
 */
@Controller
@Api(tags="测试接口")
public class TestShiroController {

    @Autowired
    public SysUserService iSysUserService;

    @Autowired
    public SysLogService sysLogService;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/test")
    @WriteLog(opPosition = "测试日志点" ,optype = CommonConstant.OPTYPE_READ)
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
    @WriteLog(opPosition = "登录系统" ,optype = CommonConstant.OPTYPE_READ)
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

    @RequestMapping("/login2")
    @ResponseBody
    @WriteLog(opPosition = "登录系统" ,optype = CommonConstant.OPTYPE_READ)
    public JSONObject login2(String name, String password, Model model){
        SysUser sysUser = new SysUser();
        sysUser = iSysUserService.getUserByName(name);
        if(sysUser==null) {
            throw new RunningException("该用户不存在！");
        }else {
            // 密码验证
            String userpassword = EncoderUtil.encrypt(name, password, sysUser.getLoginName());
            String syspassword = sysUser.getPassword();
            // if(!syspassword.equals(userpassword)) {
            //     sysLogService.addLog("登录失败，用户:"+username+"密码输入错误！", CommonConstant.LOG_TYPE_1, 3);
            //     throw new UserPasswordException("密码错误！");
            // }
            JSONObject jsonObject = new JSONObject();
            //生成token
            String token = JwtUtil.sign(name, syspassword);
            redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, token);
            //设置超时时间
            redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME/1000);
            jsonObject.put("token", token);
            jsonObject.put("userInfo", sysUser);

            return jsonObject;
        }
    }



}
