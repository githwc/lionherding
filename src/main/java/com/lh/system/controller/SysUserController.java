package com.lh.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lh.common.config.exception.RunException.RunningException;
import com.lh.common.utils.BasisUtil;
import com.lh.system.entity.SysUser;
import com.lh.system.entity.SysUserRole;
import com.lh.system.service.SysUserRoleService;
import com.lh.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    public SysUserRoleService sysUserRoleService;

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
    @ApiOperation(value = "用户退出",notes = "用户退出")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        iSysUserService.logout(request,response);
    }

    /**
     * 部门用户列表
     */
    @PostMapping(value = "/departUserList")
    @ApiOperation(value = "指定部门下的用户",notes = "指定部门下的用户")
    public Page<SysUser> departUserList(@RequestBody JSONObject jsonObject) {
        try {
            int pageNo = jsonObject.getJSONObject("page").getIntValue("pageNo");
            int pageSize = jsonObject.getJSONObject("page").getIntValue("pageSize");
            return iSysUserService.departUserList(new Page<>(pageNo, pageSize), jsonObject.getJSONObject("params"));
        }catch (Exception e){
            throw new RunningException("系统错误！"+e.getMessage());
        }
    }

    /**
     * 用户添加
     * @param jsonObject
     */
    @PostMapping(value = "/add")
    @ApiOperation(value = "用户添加",notes = "用户添加")
    public void add(@RequestBody JSONObject jsonObject) {
        try {
            iSysUserService.addUserWithRole(jsonObject);
        } catch (Exception e) {
            throw new RunningException( e.getMessage() == "" ? "操作失败" : e.getMessage());
        }
    }

    /**
     * 用户修改
     * @param jsonObject
     */
    @PutMapping(value = "/edit")
    @ApiOperation(value = "用户修改",notes = "用户修改")
    public void edit(@RequestBody JSONObject jsonObject) {
        try {
            iSysUserService.editUserWithRole(jsonObject);
        } catch (Exception e) {
            throw new RunningException( e.getMessage() == "" ? "操作失败" : e.getMessage());
        }
    }

    /**
     * 检测登录账号唯一性检验
     */
    @GetMapping("/checkIsOnly")
    @ApiOperation(value = "账号唯一性检测",notes = "账号唯一性检测")
    public void checkIsOnly(String loginName){
        try {
            iSysUserService.checkIsOnly(loginName);
        }catch (Exception e){
            throw new RunningException(e.getMessage() == "" ? "系统错误" : e.getMessage());
        }
    }

    /**
     * 删除用户
     * @param sysUserId
     * @return
     */
    @DeleteMapping(value = "/delete")
    @ApiOperation(value = "删除用户",notes = "删除用户")
    public void delete(@RequestParam(name = "sysUserId", required = true) String sysUserId) {
        try {
            iSysUserService.deleteUser(sysUserId);
        } catch (Exception e) {
            throw new RunningException(e.getMessage());
        }
    }

    /**
     * 批量删除用户
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/deleteBatch")
    @ApiOperation(value = "批量删除用户",notes = "批量删除用户")
    public void deleteBatch(@RequestParam(name = "sysUserIds", required = true) String ids) {
        try {
            String[] arr = ids.split(",");
            for (String id : arr) {
                if (BasisUtil.isNotEmpty(id)) {
                    iSysUserService.deleteUser(id);
                }
            }
        } catch (Exception e) {
            throw new RunningException(e.getMessage());
        }
    }

    /**
     * 查询用户拥有角色
     * @param userid
     * @return
     */
    @GetMapping(value = "/queryUserRole")
    @ApiOperation(value = "查询用户拥有角色",notes = "查询用户拥有角色")
    public List<String> queryUserRole(@RequestParam(name = "sysUserId", required = true) String userid) {
        List<String> list = new ArrayList<String>();
        List<SysUserRole> userRole = sysUserRoleService.list(new QueryWrapper<SysUserRole>().lambda().eq(SysUserRole::getUserId, userid));
        if (userRole == null || userRole.size() <= 0) {
            throw new RunningException("未找到用户相关角色信息");
        } else {
            for (SysUserRole sysUserRole : userRole) {
                list.add(sysUserRole.getRoleId());
            }
        }
        return list;
    }
}
