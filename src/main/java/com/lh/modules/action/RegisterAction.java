package com.lh.modules.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 功能描述：注册相关
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author: 牧狮&&紫色年华
 * @Datetime: 2019-06-12
 * @Version: 1.0.0
 */
@Controller
public class RegisterAction {

    /**
    * @Description:To注册页面
    * @Date: 22:13 2019/6/12
    * @Param:
    * @Return:
    * @throws:
    */
    @GetMapping("/regUI")
    public String toRegUI(){
        System.out.println("===LionHerding===值="  + "," + "当前类=RegisterAction.toRegUI()");
        return "register";
    }

    @RequestMapping(value = "/reg/register",method = RequestMethod.POST)
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("email") String email,
                           Map<String,Object> map,
                           HttpSession session){
        System.out.println("===LionHerding===值=" + username + "," + "当前类=RegisterAction.register()");
        System.out.println("===LionHerding===值=" + password + "," + "当前类=RegisterAction.register()");
        System.out.println("===LionHerding===值=" + email + "," + "当前类=RegisterAction.register()");
        System.out.println("===LionHerding===值="  + "," + "当前类=RegisterAction.toRegUI()");
        return "ss";
    }

}
