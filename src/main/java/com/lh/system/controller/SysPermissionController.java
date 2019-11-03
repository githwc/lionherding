package com.lh.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.lh.common.config.exception.RunException.RunningException;
import com.lh.common.config.response.HttpResponseUtil;
import com.lh.common.config.response.ResponseBean;
import com.lh.common.config.response.ResponseCode;
import com.lh.common.utils.BasisUtil;
import com.lh.system.entity.SysPermission;
import com.lh.system.service.SysPermissionService;
import com.lh.system.utils.PermissionOPUtil;
import com.lh.system.vo.SysPermissionTree;
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

    @Autowired
    private SysPermissionService service;

    @GetMapping(value = "/getUserPermissionByToken")
    @ApiOperation(value = "获取用户权限",notes = "根据Token获取用户拥有的权限")
    public JSONObject getUserPermissionByToken(@RequestParam(name = "token", required = true) String token, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = service.getUserPermissionByToken(token,response);
        }catch (Exception e){
            HttpResponseUtil.sendJson(response, ResponseBean.error(ResponseCode.SYSTEM_EXCEPTION,e.getMessage()!= "" ? e.getMessage() :"系统错误，请联系管理员！"));
            log.error(e.getMessage(), e);
        }finally {
            return jsonObject;
        }
    }

    @ApiOperation(value = "查询全部权限",notes = "查询全部权限")
    @GetMapping(value = "/list")
    public List<SysPermissionTree> list() {
        try {
            return service.permissionlist();
        } catch (Exception e) {
            throw new RunningException(e.getMessage());
        }
    }


    @ApiOperation(value = "获取权限树",notes = "获取权限树")
    @RequestMapping(value = "/queryTreeList", method = RequestMethod.GET)
    public Map<String, Object> queryTreeList() {
        try{
            return service.queryTreeList();
        }catch (Exception e){
            throw new RunningException("系统运行错误");
        }
    }

    @PostMapping(value = "/add")
    @ApiOperation(value = "添加菜单",notes = "添加菜单")
    public void add(@RequestBody SysPermission permission) {
        try{
            permission = PermissionOPUtil.intelligentProcessData(permission);
            service.addPermission(permission);
        }catch (Exception e){
            throw new RunningException("操作失败");
        }
    }

    @PutMapping(value = "/edit")
    @ApiOperation(value = "编辑菜单",notes = "编辑菜单")
    public void edit(@RequestBody SysPermission permission) {
        try {
            permission = PermissionOPUtil.intelligentProcessData(permission);
            service.editPermission(permission);
        } catch (Exception e) {
            throw new RunningException(e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete")
    @ApiOperation(value = "删除菜单",notes = "删除菜单")
    public void delete(@RequestParam(name = "sysPermissionId", required = true) String id) {
        try {
            service.deletePermission(id);
        } catch (Exception e) {
            throw new RunningException(e.getMessage());
        }
    }

    @DeleteMapping(value = "/deleteBatch")
    @ApiOperation(value = "批量删除菜单" ,notes = "批量删除菜单")
    public void deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        try {
            String[] arr = ids.split(",");
            for (String id : arr) {
                if (BasisUtil.isNotEmpty(id)) {
                    service.deletePermission(id);
                }
            }
        } catch (Exception e) {
            throw new RunningException(e.getMessage());
        }
    }

    @GetMapping(value = "/queryRolePermission")
    @ApiOperation(value = "查询角色授权", notes = "查询角色拥有的权限")
    public List<String> queryRolePermission(@RequestParam(name = "sysRoleId", required = true) String roleId) {
        try {
            return service.queryRolePermission(roleId);
        } catch (Exception e) {
            throw new RunningException(e.getMessage());
        }
    }

    @PostMapping(value = "/saveRolePermission")
    @ApiOperation(value = "保存角色授权", notes = "保存角色拥有的权限")
    public void saveRolePermission(@RequestBody JSONObject json) {
        try {
            service.saveRolePermission(json);
        } catch (Exception e) {
            throw new RunningException(e.getMessage());
        }
    }

}
