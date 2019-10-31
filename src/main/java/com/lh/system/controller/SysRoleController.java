package com.lh.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lh.common.config.exception.userException.RunningException;
import com.lh.common.constant.CommonConstant;
import com.lh.system.entity.SysRole;
import com.lh.system.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
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
@Api(tags = "系统角色")
public class SysRoleController {

    @Autowired
    public SysRoleService service;

    /**
     * 加载有效角色
     * @return
     */
    @GetMapping(value = "/queryall")
    @ApiOperation(value = "查询所有角色",notes = "查询所有角色")
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

    /**
     * 加载有效角色
     * @return
     */
    @PostMapping(value = "/queryPageAll")
    @ApiOperation(value = "查询所有角色",notes = "查询所有角色")
    public Page<SysRole> queryPageAll(@RequestBody JSONObject jsonObject) {
        try{
            int pageNo = jsonObject.getJSONObject("page").getIntValue("pageNo");
            int pageSize = jsonObject.getJSONObject("page").getIntValue("pageSize");
            return service.queryPageAll(new Page<>(pageNo, pageSize));
        }catch (Exception e){
            throw new RunningException(e.getMessage() == "" ? "系统运行错误" : e.getMessage());
        }
    }

    /**
     *   添加
     * @param sysRole
     * @return
     */
    @ApiOperation(value = "角色添加",notes = "角色添加")
    @PostMapping(value = "/add")
    public void add(@RequestBody SysRole sysRole) {
        try {
            service.save(sysRole);
        } catch (Exception e) {
            throw new RunningException("系统错误!");
        }
    }

    /**
     *  编辑
     * @param role
     * @return
     */
    @PutMapping(value = "/edit")
    @ApiOperation(value = "角色修改",notes = "角色修改")
    public void edit(@RequestBody SysRole role) {
        try {
            service.updateById(role);
        }catch (Exception e){
            throw new RunningException("系统错误");
        }
    }

    /**
     *   通过id删除
     * @param id
     * @return
     */
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

    /**
     *  批量删除
     * @param ids
     * @return
     */
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

}
