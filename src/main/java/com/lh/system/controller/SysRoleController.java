package com.lh.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lh.common.config.exception.RunException.RunningException;
import com.lh.common.constant.CommonConstant;
import com.lh.system.entity.SysRole;
import com.lh.system.entity.SysUser;
import com.lh.system.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
@Api(tags = "系统角色")
public class SysRoleController {

    @Autowired
    public SysRoleService service;

    @PostMapping(value = "/queryPageAll")
    @ApiOperation(value = "查询所有角色",notes = "加载所有角色(分页)")
    public Page<SysRole> queryPageAll(@RequestBody JSONObject jsonObject) {
        try{
            int pageNo = jsonObject.getJSONObject("page").getIntValue("pageNo");
            int pageSize = jsonObject.getJSONObject("page").getIntValue("pageSize");
            return service.queryPageAll(new Page<>(pageNo, pageSize),jsonObject.getJSONObject("params"));
        }catch (Exception e){
            throw new RunningException(e.getMessage() == "" ? "系统运行错误" : e.getMessage());
        }
    }

    @GetMapping(value = "/queryall")
    @ApiOperation(value = "查询所有角色",notes = "查询所有角色(新增用户时调用)")
    public List<SysRole> queryall() {
        try{
            return service.roleList();
        }catch (Exception e){
            throw new RunningException(e.getMessage() == "" ? "系统运行错误" : e.getMessage());
        }
    }

    @ApiOperation(value = "角色添加",notes = "角色添加")
    @PostMapping(value = "/add")
    public void add(@RequestBody SysRole sysRole) {
        try {
            SysUser currUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
            sysRole.setCreateUserId(currUser.getSysUserId());
            service.save(sysRole);
        } catch (Exception e) {
            throw new RunningException("系统错误!");
        }
    }

    @PutMapping(value = "/edit")
    @ApiOperation(value = "角色修改",notes = "角色修改")
    public void edit(@RequestBody SysRole role) {
        try {
            service.updateById(role);
        }catch (Exception e){
            throw new RunningException("系统错误");
        }
    }

    @DeleteMapping(value = "/delete")
    @ApiOperation(value = "角色删除",notes = "角色删除")
    public void delete(@RequestParam(name="sysRoleId",required=true) String id) {
        try {
            SysRole sysRole = new SysRole();
            sysRole.setSysRoleId(id);
            sysRole.setDelFlag(CommonConstant.DEL_FLAG_1);
            service.updateById(sysRole);
        }catch (Exception e) {
            throw new RunningException("系统错误");
        }
    }

    @DeleteMapping(value = "/deleteBatch")
    @ApiOperation(value = "角色批量删除",notes = "角色批量删除")
    public void deleteBatch(@RequestParam(name="ids",required=true) String ids) {
        try {
            List<String> list_id = Arrays.asList(ids.split(","));
            list_id.forEach(curr->{
                SysRole sysRole = new SysRole();
                sysRole.setSysRoleId(curr);
                sysRole.setDelFlag(CommonConstant.DEL_FLAG_1);
                service.updateById(sysRole);
            });
        }catch (Exception e) {
            throw new RunningException("系统错误");
        }
    }

    @GetMapping("/duplicate")
    @ApiOperation(value = "重复校验",notes = "角色代码唯一性校验")
    public void duplicate(@RequestParam("roleCode") String roleCode){
        try {
            service.duplicate(roleCode);
        }catch (Exception e){
            throw new RunningException(e.getMessage() == "" ? "系统错误" : e.getMessage());
        }
    }

    @GetMapping(value = "/queryTreeList")
    @ApiOperation(value = "查看菜单权限树",notes = "查看菜单权限树")
    public Map<String,Object> queryTreeList(HttpServletRequest request) {
        try {
            return service.queryTreeList();
        }catch (Exception e){
            throw new RunningException(e.getMessage()!= "" ? e.getMessage():"系统错误");
        }
    }

}
