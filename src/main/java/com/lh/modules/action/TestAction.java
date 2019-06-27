package com.lh.modules.action;

import com.lh.modules.model.Test;
import com.lh.modules.service.TestService;
import com.lh.system.basis.BasisAction;
import com.lh.system.log.SystemLogService;
import com.lh.system.log.WriteLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/test")
public class TestAction  {

    @Autowired
    private TestService testService;

    @RequestMapping("/hello")
    @WriteLog(mName = "测试", optype = SystemLogService.OPTYPE_READ)
    public String hello(){
        return "d";
    }

    @RequestMapping("/getInfo")
    public Test getInfo(){
        Test dd = testService.getInfo();
        return dd;
    }


    /**
    * @Description:通用mapper - insert
    * @Date: 21:32 2019/6/27
    * @Param:
    * @Return:
    * @throws:
    */
    @RequestMapping("/add")
    public void addTest(){
        testService.addTest();
    }

    /**
    * @Description: 通用Mapper - update
    * @Date: 23:14 2019/6/27
    * @Param:
    * @Return:
    * @throws:
    */
    @RequestMapping("/updateInfo")
    public void updateInfo(){
        testService.updateInfo();
    }

    /**
    * @Description:通用Mapper -select
    * @Date: 23:11 2019/6/27
    * @Param:
    * @Return:
    * @throws:
    */
    @RequestMapping("/getInfoById/{id}")
    public Test getInfoById(@PathVariable String id){
        return testService.getInfoById(id);
    }
}

