package com.lh.system.service;

import com.lh.system.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * 功能描述：
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
public interface SysUserService extends IService<SysUser> {

    boolean insertOrUpdate(SysUser iSysUser);

    SysUser getUserByName(String loginName);

    Set<String> getUserRoles(String loginName);

    Set<String> getUserPermissions(String loginName);
}
