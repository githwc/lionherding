package com.lh.system.controller;

import com.lh.common.config.exception.RunException.RunningException;
import com.lh.common.constant.CommonConstant;
import com.lh.common.log.WriteLog;
import com.lh.system.service.SysUserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 *
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author  xieyc
 * @Date 2019-09-19
 * @Version: 1.0.0
 *
 */
@RestController
@RequestMapping("/sysUserRole")
@Slf4j
@Api(tags = "系统(用户-角色)")
public class SysUserRoleController {

    private final SysUserRoleService service;

    @Autowired
    public SysUserRoleController(SysUserRoleService service) {
        this.service = service;
    }

    @GetMapping(value = "/queryUserRoleMap")
    @ApiOperation(value = "查询用户所对应的角色信息",notes = "查询用户所对应的角色信息")
    @WriteLog(opPosition = "查询用户所对应的角色信息" ,optype = CommonConstant.OPTYPE_READ)
    public Map<String, String> queryUserRole() {
        try{
            return service.queryUserRole();
        }catch (Exception e){
            throw new RunningException("".equals(e.getMessage()) ?  "系统错误,请联系管理员！" : e.getMessage());
        }
    }

}
