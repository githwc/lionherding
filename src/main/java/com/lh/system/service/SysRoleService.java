package com.lh.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lh.system.entity.SysRole;
import com.lh.system.model.query.RoleQuery;

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
 * @Date 2019-09-19
 * @Version: 1.0.0
 *
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 获取用户权限
     * @param loginName 登录名称
     * @return
     */
    Set<String> getUserRoles(String loginName);

    /**
     * 加载有效角色
     * @return
     */
    List<SysRole> roleList();

    /**
     * 分页查询所有角色
     * @param page
     * @param roleQuery
     * @return
     */
    Page<SysRole> rolePage(Page<SysRole> page, RoleQuery roleQuery);

    /**
     * 角色代码唯一性校验
     * @param roleCode
     */
    void duplicate(String roleCode);

}
