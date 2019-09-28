package com.lh.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lh.system.entity.SysPermission;

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
     * 获取用户权限 例如：admin,guest,xxx
     * @param loginName
     * @return
     */
    Set<String> getUserPermissions(String loginName);

    /**
     * 获取用户权限
     * @param loginName
     * @return
     */
    List<SysPermission> queryPermissionByUser(String loginName);
}
