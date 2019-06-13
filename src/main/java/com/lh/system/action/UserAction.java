package com.lh.system.action;

import com.lh.system.log.SystemLogService;
import com.lh.system.log.WriteLog;
import com.lh.system.model.User;
import com.lh.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 功能描述：
 * <p>
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author: 牧狮&&紫色年华
 * @Datetime: 2019-05-31 17:45
 */
@Controller
@RequestMapping("/user")
public class UserAction {

    @Autowired
    private UserService userService;
    /**
     * @Description:用户自行注册
     * @Date: 2019/6/13 13:34
     * @Param:
     * @Return:
     * @throws:
     */
    @PostMapping(value = "/register")
    @ResponseBody
    public String register(User user, Map<String,Object> map){
        //保证登录名的唯一性
        User onlyUser = userService.getUserInfo(user.getLoginName());
        if(onlyUser!=null){
            return "自行注册失败,登录名重复，请更换登录名";
        }
        //注册用户
        int result = userService.register(user);
        if(result>0){
            return "自行注册成功！";
        }else {
            return "自行注册失败";
        }
    }

    /**
     * @Description:用户登录
     * @Date: 2019/6/13 15:05
     * @Param:
     * @Return:
     * @throws:
     */
    @PostMapping(value = "/login")
    @WriteLog(mName = "登录", optype = SystemLogService.OPTYPE_READ)
    public String login(@RequestParam("login_loginName")String loginName,
                        @RequestParam("login_password")String password,
                        HttpServletRequest request, HttpServletResponse response
    ){
        return userService.login(loginName,password,request,response);
    }
}
