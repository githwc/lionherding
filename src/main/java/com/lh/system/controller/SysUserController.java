package com.lh.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lh.common.config.exception.RunException.RunningException;
import com.lh.common.constant.CommonConstant;
import com.lh.common.log.WriteLog;
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

    @PostMapping(value = "/logout")
    @ApiOperation(value = "用户登出",notes = "用户登出")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        iSysUserService.logout(request,response);
    }

    @PostMapping(value = "/departUserList")
    @ApiOperation(value = "加载用户",notes = "查询某个部门下的有效用户")
    @WriteLog(opPosition = "加载用户" ,optype = CommonConstant.OPTYPE_READ)
    public Page<SysUser> departUserList(@RequestBody JSONObject jsonObject) {
        try {
            int pageNo = jsonObject.getJSONObject("page").getIntValue("pageNo");
            int pageSize = jsonObject.getJSONObject("page").getIntValue("pageSize");
            return iSysUserService.departUserList(new Page<>(pageNo, pageSize), jsonObject.getJSONObject("params"));
        }catch (Exception e){
            throw new RunningException("系统错误！"+e.getMessage());
        }
    }

    @PostMapping(value = "/add")
    @ApiOperation(value = "用户添加",notes = "用户添加")
    @WriteLog(opPosition = "用户添加" ,optype = CommonConstant.OPTYPE_CREATE)
    public void add(@RequestBody JSONObject jsonObject) {
        try {
            iSysUserService.addUserWithRole(jsonObject);
        } catch (Exception e) {
            throw new RunningException( e.getMessage() == "" ? "操作失败" : e.getMessage());
        }
    }

    @PutMapping(value = "/edit")
    @ApiOperation(value = "用户修改",notes = "用户修改")
    @WriteLog(opPosition = "用户修改" ,optype = CommonConstant.OPTYPE_UPDATE)
    public void edit(@RequestBody JSONObject jsonObject) {
        try {
            iSysUserService.editUserWithRole(jsonObject);
        } catch (Exception e) {
            throw new RunningException( e.getMessage() == "" ? "操作失败" : e.getMessage());
        }
    }

    @GetMapping("/checkIsOnly")
    @ApiOperation(value = "账号唯一性检测",notes = "登录账号唯一性检测")
    @WriteLog(opPosition = "账号唯一性检测" ,optype = CommonConstant.OPTYPE_READ)
    public void checkIsOnly(String loginName){
        try {
            iSysUserService.checkIsOnly(loginName);
        }catch (Exception e){
            throw new RunningException(e.getMessage() == "" ? "系统错误" : e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete")
    @ApiOperation(value = "删除用户",notes = "删除用户")
    @WriteLog(opPosition = "删除用户" ,optype = CommonConstant.OPTYPE_DELETE)
    public void delete(@RequestParam(name = "sysUserId", required = true) String sysUserId) {
        try {
            iSysUserService.deleteUser(sysUserId);
        } catch (Exception e) {
            throw new RunningException(e.getMessage());
        }
    }

    @DeleteMapping(value = "/deleteBatch")
    @ApiOperation(value = "批量删除用户",notes = "批量删除用户")
    @WriteLog(opPosition = "批量删除用户" ,optype = CommonConstant.OPTYPE_DELETE)
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

    @GetMapping(value = "/queryUserRole")
    @ApiOperation(value = "查询用户拥有角色",notes = "查询用户拥有角色")
    @WriteLog(opPosition = "查询用户拥有角色" ,optype = CommonConstant.OPTYPE_READ)
    public List<String> queryUserRole(@RequestParam(name = "sysUserId", required = true) String userid) {
        List<String> list = new ArrayList<String>();
        List<SysUserRole> userRole = sysUserRoleService.list(new LambdaQueryWrapper<SysUserRole>()
                .eq(SysUserRole::getUserId, userid));
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
