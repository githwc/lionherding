package com.lh.system.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lh.system.entity.SysPermission;
import com.lh.system.vo.SysPermissionTree;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

/**
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2019-09-20
 * @Version: 1.0.0
 *
 */
public interface SysPermissionService extends IService<SysPermission> {

    /**
     * 获取用户权限码 例如：admin,guest,xxx
     * @param loginName
     * @return
     */
    Set<String> getUserPermCodes(String loginName);

    /**
     * 根据Token获取用户拥有的权限
     * @param token
     * @param response
     * @return
     */
    JSONObject getUserPermissionByToken(String token,HttpServletResponse response);

    List<SysPermissionTree> permissionlist ();


}
