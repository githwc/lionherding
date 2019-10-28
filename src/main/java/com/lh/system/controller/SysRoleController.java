package com.lh.system.controller;

import com.lh.common.config.exception.userException.RunningException;
import com.lh.system.entity.SysRole;
import com.lh.system.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author   xieyc
 * @Date 2019-09-19
 * @Version: 1.0.0
 *
 */
@RestController
@RequestMapping("/sysRole")
@Slf4j
public class SysRoleController {

    @Autowired
    public SysRoleService service;

    /**
     * 加载有效角色
     * @return
     */
    @GetMapping(value = "/queryall")
    public List<SysRole> queryall() {
        List<SysRole> list = new ArrayList<SysRole>();
        try{
            list = service.roleList();
        }catch (Exception e){
            throw new RunningException(e.getMessage() == "" ? "系统运行错误" : e.getMessage());
        }finally {
            return list;
        }
    }



}
