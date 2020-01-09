package com.lh.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lh.common.config.exception.RunException.RunningException;
import com.lh.common.constant.CommonConstant;
import com.lh.common.log.WriteLog;
import com.lh.system.model.query.LogQuery;
import com.lh.system.model.vo.SysLogVO;
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
 * 功能描述：日志前端控制器
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
    @ApiOperation(value = "首页获取系统访问数据",notes = "首页获取系统访问数据")
    @WriteLog(opPosition = "首页获取系统访问数据" ,optype = CommonConstant.OPTYPE_READ)
    public JSONObject logInfo(){
        try{
            return service.logInfo();
        }catch(Exception e){
            throw new RunningException("".equals(e.getMessage()) ?  "系统错误,请联系管理员！" : e.getMessage());
        }
    }

    /**
     * 分页查询系统日志
     * @param page 分页信息
     * @param logQuery 查询条件
     * @return
     */
    @GetMapping("/logPage")
    public Page<SysLogVO> logPage(Page<SysLogVO> page,LogQuery logQuery){
        return service.logPage(page,logQuery);
    }

}
