package com.lh.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.lh.system.entity.SysUser;
import com.lh.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * 功能描述：系统用户 Controller
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2019-09-19
 * @Version: 1.0.0
 *
 */
@RestController
@RequestMapping("/sysUser")
@Slf4j
@Api(tags="系统用户")
public class SysUserController {

    @Autowired
    public SysUserService iSysUserService;


    @PostMapping("/login")
    @ApiOperation(value = "用户登录",notes = "用户登录")
    public JSONObject login(@RequestBody SysUser sysUser){
        return iSysUserService.login(sysUser);
    }

    /**
     * 退出登录
     * @return
     */
    @PostMapping(value = "/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        iSysUserService.logout(request,response);
    }



}
