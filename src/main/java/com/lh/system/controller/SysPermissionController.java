package com.lh.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lh.common.config.exception.parameterException.ParameterException;
import com.lh.common.config.response.HttpResponseUtil;
import com.lh.common.config.response.ResponseBean;
import com.lh.common.config.response.ResponseCode;
import com.lh.common.constant.CommonConstant;
import com.lh.system.entity.SysPermission;
import com.lh.system.service.SysPermissionService;
import com.lh.system.service.SysUserService;
import com.lh.system.vo.SysPermissionTree;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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


}
