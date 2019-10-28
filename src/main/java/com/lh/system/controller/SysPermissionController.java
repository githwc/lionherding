package com.lh.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.lh.common.config.exception.userException.RunningException;
import com.lh.common.config.response.HttpResponseUtil;
import com.lh.common.config.response.ResponseBean;
import com.lh.common.config.response.ResponseCode;
import com.lh.common.utils.BasisUtil;
import com.lh.system.entity.SysPermission;
import com.lh.system.service.SysPermissionService;
import com.lh.system.utils.PermissionOPUtil;
import com.lh.system.vo.SysPermissionTree;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
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
 * @Date 2019-09-20
 * @Version: 1.0.0
 *
 */
@RestController
@RequestMapping("/sysPermission")
@Slf4j
public class SysPermissionController {

    @Autowired
    public SysPermissionService service;

    /**
     *  根据Token获取用户拥有的权限
     * @param token
     * @param response
     * @return
     */
    @GetMapping(value = "/getUserPermissionByToken")
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

    /**
    * @Description: 加载全部菜单有效数据
    * @Date: 14:25 2019/10/25
    * @Param:
    * @Return:
    * @throws:
    */
    @GetMapping(value = "/list")
    public List<SysPermissionTree> list() {
        List<SysPermissionTree> list = new ArrayList<>();
        try {
            list = service.permissionlist();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }finally {
            return list;
        }
    }

    /**
     * 获取全部的权限树
     *
     * @return
     */
    @RequestMapping(value = "/queryTreeList", method = RequestMethod.GET)
    public Map<String, Object> queryTreeList() {
        Map<String, Object> result = new HashMap<>();
        try{
            result = service.queryTreeList();
        }catch (Exception e){
            throw new RunningException("系统运行错误");
        }finally {
            return result;
        }
    }

    /**
     * 添加菜单
     * @param permission
     * @return
     */
    @PostMapping(value = "/add")
    public void add(@RequestBody SysPermission permission) {
        try{
            permission = PermissionOPUtil.intelligentProcessData(permission);
            service.addPermission(permission);
        }catch (Exception e){
            throw new RunningException("操作失败");
        }
    }

    /**
     * 编辑菜单
     * @param permission
     * @return
     */
    @PutMapping(value = "/edit")
    public void edit(@RequestBody SysPermission permission) {
        try {
            permission = PermissionOPUtil.intelligentProcessData(permission);
            service.editPermission(permission);
        } catch (Exception e) {
            throw new RunningException(e.getMessage());
        }
    }


    /**
     * 删除菜单
     * @param id
     * @return
     */
    @DeleteMapping(value = "/delete")
    public void delete(@RequestParam(name = "sysPermissionId", required = true) String id) {
        try {
            service.deletePermission(id);
        } catch (Exception e) {
            throw new RunningException(e.getMessage());
        }
    }

    /**
     * 批量删除菜单
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/deleteBatch")
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
}
