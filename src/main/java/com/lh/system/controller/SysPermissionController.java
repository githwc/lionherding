package com.lh.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.lh.common.config.exception.RunException.RunningException;
import com.lh.common.constant.CommonConstant;
import com.lh.common.log.WriteLog;
import com.lh.system.entity.SysPermission;
import com.lh.system.model.vo.SysPermissionTree;
import com.lh.system.service.SysPermissionService;
import com.lh.system.utils.PermissionOPUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 *
 * 功能描述：权限控制器 (菜单 按钮)
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
@Api(tags = "系统权限")
public class SysPermissionController {

    private final SysPermissionService service;

    @Autowired
    public SysPermissionController(SysPermissionService service) {
        this.service = service;
    }

    @GetMapping(value = "/getUserPermissionByToken")
    @ApiOperation(value = "获取用户权限",notes = "根据Token获取用户拥有的权限")
    @WriteLog(opPosition = "获取指定用户权限" ,optype = CommonConstant.OPTYPE_READ)
    public JSONObject getUserPermissionByToken(@RequestParam("token") String token, HttpServletResponse response) {
        try {
            return service.getUserPermissionByToken(token,response);
        }catch (Exception e){
            throw new RunningException("".equals(e.getMessage()) ?  "系统错误,请联系管理员！" : e.getMessage());
        }
    }

    @GetMapping(value = "/list")
    @ApiOperation(value = "查询全部权限",notes = "查询全部权限")
    @WriteLog(opPosition = "查询全部权限" ,optype = CommonConstant.OPTYPE_READ)
    public List<SysPermissionTree> list() {
        try {
            return service.permissionlist();
        } catch (Exception e) {
            throw new RunningException("".equals(e.getMessage()) ?  "系统错误,请联系管理员！" : e.getMessage());
        }
    }

    @GetMapping(value = "/queryTreeList")
    @ApiOperation(value = "获取权限树",notes = "获取权限树")
    @WriteLog(opPosition = "获取权限树" ,optype = CommonConstant.OPTYPE_READ)
    public Map<String, Object> queryTreeList() {
        try{
            return service.queryTreeList();
        }catch (Exception e){
            throw new RunningException("".equals(e.getMessage()) ?  "系统错误,请联系管理员！" : e.getMessage());
        }
    }

    @PostMapping(value = "/add")
    @ApiOperation(value = "添加菜单",notes = "添加菜单")
    @WriteLog(opPosition = "添加菜单" ,optype = CommonConstant.OPTYPE_CREATE)
    public void add(@RequestBody SysPermission permission) {
        try{
            permission = PermissionOPUtil.intelligentProcessData(permission);
            service.addPermission(permission);
        }catch (Exception e){
            throw new RunningException("".equals(e.getMessage()) ?  "系统错误,请联系管理员！" : e.getMessage());
        }
    }

    @PutMapping(value = "/edit")
    @ApiOperation(value = "编辑菜单",notes = "编辑菜单")
    @WriteLog(opPosition = "编辑菜单" ,optype = CommonConstant.OPTYPE_UPDATE)
    public void edit(@RequestBody SysPermission permission) {
        try {
            permission = PermissionOPUtil.intelligentProcessData(permission);
            service.editPermission(permission);
        } catch (Exception e) {
            throw new RunningException("".equals(e.getMessage()) ?  "系统错误,请联系管理员！" : e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete")
    @ApiOperation(value = "删除菜单",notes = "删除菜单")
    @WriteLog(opPosition = "删除菜单" ,optype = CommonConstant.OPTYPE_DELETE)
    public void delete(@RequestParam("sysPermissionId") String id) {
        try {
            service.deletePermission(id);
        } catch (Exception e) {
            throw new RunningException("".equals(e.getMessage()) ?  "系统错误,请联系管理员！" : e.getMessage());
        }
    }

    @DeleteMapping(value = "/deleteBatch")
    @ApiOperation(value = "批量删除菜单" ,notes = "批量删除菜单")
    @WriteLog(opPosition = "批量删除菜单" ,optype = CommonConstant.OPTYPE_DELETE)
    public void deleteBatch(@RequestParam("ids") String ids) {
        try {
            service.deleteBatch(ids);
        } catch (Exception e) {
            throw new RunningException("".equals(e.getMessage()) ?  "系统错误,请联系管理员！" : e.getMessage());
        }
    }

    @GetMapping(value = "/queryRolePermission")
    @ApiOperation(value = "查询角色授权", notes = "查询角色拥有的权限")
    @WriteLog(opPosition = "查询角色授权" ,optype = CommonConstant.OPTYPE_READ)
    public List<String> queryRolePermission(@RequestParam("sysRoleId") String roleId) {
        try {
            return service.queryRolePermission(roleId);
        } catch (Exception e) {
            throw new RunningException("".equals(e.getMessage()) ?  "系统错误,请联系管理员！" : e.getMessage());
        }
    }

    @PostMapping(value = "/saveRolePermission")
    @ApiOperation(value = "保存角色授权", notes = "保存角色拥有的权限")
    @WriteLog(opPosition = "保存角色授权" ,optype = CommonConstant.OPTYPE_CREATE)
    public void saveRolePermission(@RequestBody JSONObject json) {
        try {
            service.saveRolePermission(json);
        } catch (Exception e) {
            throw new RunningException("".equals(e.getMessage()) ?  "系统错误,请联系管理员！" : e.getMessage());
        }
    }

}
