package com.lh.modules.action;

import com.lh.modules.model.Test;
import com.lh.modules.service.TestService;
import com.lh.system.basis.Result;
import com.lh.system.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述：
 * <p>
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author: 牧狮&&紫色年华
 * @Datetime: 2019-05-30 10:53
 */
@RestController
public class TestAction {

    @Autowired
    private TestService testService;

    @RequestMapping("/hello")
    // @WriteLog(mName = "测试", optype = SystemLogService.OPTYPE_READ)
    public String hello(){
        return "d";
    }

    @RequestMapping("/hi")
    public Result<User> hi(){
        User user = testService.queryByid("123");
        return Result.success(user);
    }

    @RequestMapping("/getInfo")
    public Test getInfo(){
        Test dd = testService.getInfo();
        return dd;
    }
}

