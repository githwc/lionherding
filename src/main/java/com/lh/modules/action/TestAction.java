package com.lh.modules.action;

import com.lh.system.log.SystemLogService;
import com.lh.system.log.WriteLog;
import org.springframework.stereotype.Controller;
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

    @ResponseBody
    @RequestMapping("/hello")
    @WriteLog(mName = "测试", optype = SystemLogService.OPTYPE_READ)
    public String hello(){
        return "d";
    }
}
