package com.lh.modules.Test.controller;

import com.lh.modules.Test.entity.Test;
import com.lh.modules.Test.service.TestService;
import com.lh.system.log.SystemLogService;
import com.lh.system.log.WriteLog;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@Api(tags = "测试相关")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/hello")
    @WriteLog(mName = "测试", optype = SystemLogService.OPTYPE_READ) public String hello(){
        return "d";
    }

    @PostMapping("/getInfo")
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

    /**
     * @Description:通用Mapper - select
     * @Date: 2019/6/28 9:45
     * @Param:
     * @Return:
     * @throws:
     */
    @RequestMapping("/select")
    public void select(){
        testService.select();
    }

    /**
     * @Description:通用mapper - delete
     * @Date: 2019/6/28 13:51
     * @Param:
     * @Return:
     * @throws:
     */
    @RequestMapping("/delete")
    public void delete(){
        testService.delete();
    }

    /**
     * @Description:条件构造器 - select
     * @Date: 2019/6/28 14:02
     * @Param:
     * @Return:
     * @throws:
     */
    @RequestMapping("/selectConditions")
    public void selectConditions(){
        testService.selectConditions();
    }

    /**
     * @Description:条件构造器 - update
     * @Date: 2019/6/28 14:11
     * @Param:
     * @Return:
     * @throws:
     */
    @RequestMapping("/updateConditions")
    public void updateConditions(){
        testService.updateConditions();
    }

    /**
     * @Description:条件构造器 - 删除
     * @Date: 2019/6/28 14:19
     * @Param:
     * @Return:
     * @throws:
     */
    @RequestMapping("/deleteConditions")
    public void deleteConditions(){
        testService.deleteConditions();
    }

    /**
    * @Description:删除所有  -- 测试执行分析插件
    * @Date: 22:08 2019/6/28
    * @Param:
    * @Return:
    * @throws:
    */
    @RequestMapping("/deleteAll")
    public void deleteAll(){
        testService.deleteAll();
    }
}

