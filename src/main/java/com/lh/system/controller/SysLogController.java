package com.lh.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.lh.common.config.exception.RunException.RunningException;
import com.lh.common.constant.CommonConstant;
import com.lh.common.log.WriteLog;
import com.lh.system.service.SysLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 功能描述：系统日志
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2019-09-21
 * @Version: 1.0.0
 *
 */
@RestController
@RequestMapping("/sysLog")
@Slf4j
@Api(tags = "系统日志")
public class SysLogController {

    private final SysLogService service;

    @Autowired
    public SysLogController(SysLogService service) {
        this.service = service;
    }

    @GetMapping("/logInfo")
    @ApiOperation(value = "获取系统日志",notes = "获取系统日志")
    @WriteLog(opPosition = "获取系统日志" ,optype = CommonConstant.OPTYPE_READ)
    public JSONObject logInfo(){
        try{
            return service.logInfo();
        }catch(Exception e){
            throw new RunningException(e.getMessage() .equals("") ?  "系统错误,请联系管理员！" : e.getMessage());
        }
    }

}
