package com.lh.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.lh.system.service.SysPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 *
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author   xieyc
 * @Date 2019-09-20
 * @Version: 1.0.0
 *
 */
@RestController
@RequestMapping("/sysPermission")
@Slf4j
public class SysPermissionController {

    @Autowired
    public SysPermissionService iSysPermissionService;

    /**
     *  查询用户拥有的菜单权限和按钮权限(根据TOKEN)
     * @param token
     * @param response
     * @return
     */
    @GetMapping(value = "/getUserPermissionByToken")
    public JSONObject getUserPermissionByToken(@RequestParam(name = "token", required = true) String token, HttpServletResponse response) {
        return iSysPermissionService.getUserPermissionByToken(token,response);
    }

}
