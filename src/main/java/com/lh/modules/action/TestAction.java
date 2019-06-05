package com.lh.modules.action;

import com.lh.modules.service.Test;
import com.lh.system.log.SystemLogService;
import com.lh.system.log.WriteLog;
import com.lh.system.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
@Controller
public class TestAction {

    @Autowired
    private Test test;

    @ResponseBody
    @RequestMapping("/hello")
    @WriteLog(mName = "测试", optype = SystemLogService.OPTYPE_READ)
    public String hello(){
        return "d";
    }

    @ResponseBody
    @RequestMapping("/hi")
    public String hi(){
        User user = test.queryByid("123");
        System.out.println("===LionHerding===值=" + user + "," + "当前类=TestAction.hi()");
        return "23423";
    }
}
