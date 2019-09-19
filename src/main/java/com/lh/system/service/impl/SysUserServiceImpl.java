package com.lh.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lh.system.entity.SysPermission;
import com.lh.system.entity.SysUser;
import com.lh.system.mapper.SysPermissionMapper;
import com.lh.system.mapper.SysRoleMapper;
import com.lh.system.mapper.SysUserMapper;
import com.lh.system.mapper.SysUserRoleMapper;
import com.lh.system.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.framework.core.utils.oConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
* 功能描述：
*
*  <p>版权所有：</p>
*  未经本人许可，不得以任何方式复制或使用本程序任何部分
*
* @Company: LionHerding
* @Author 牧狮&&紫色年华
* @Date 2019-09-19
* @Version: 1.0.0
*
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public boolean insertOrUpdate(SysUser iSysUser) {
        boolean result = false;
        return result;
    }

    @Override
    public SysUser getUserByName(String userName) {
        return this.baseMapper.selectOne(new QueryWrapper<SysUser>()
                .eq("username",userName));
    }

    @Override
    public Set<String> getUserRoles(String userName) {
        List<String> rolesList = sysRoleMapper.getUserRoles(userName);
        return new HashSet<String>(rolesList);
    }

    @Override
    public Set<String> getUserPermissions(String username) {
        Set<String> permissionSet = new HashSet<String>();
        List<SysPermission> permissionList = sysPermissionMapper.queryByUser(username);
        for (SysPermission po : permissionList) {
            // TODO URL规则有问题？
            if (oConvertUtils.isNotEmpty(po.getPerms())) {
                permissionSet.add(po.getPerms());
            }
        }
        return permissionSet;
    }
}
