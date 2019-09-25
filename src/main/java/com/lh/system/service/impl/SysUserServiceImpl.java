package com.lh.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lh.common.utils.BasisUtil;
import com.lh.system.entity.SysPermission;
import com.lh.system.entity.SysUser;
import com.lh.system.mapper.SysPermissionMapper;
import com.lh.system.mapper.SysRoleMapper;
import com.lh.system.mapper.SysUserMapper;
import com.lh.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
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
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public SysUser getUserByName(String loginName) {
        return this.baseMapper.selectOne(new QueryWrapper<SysUser>()
                .eq("login_name",loginName));
    }

    @Override
    public void dealUser(SysUser sysUser) {
        // todo 验证此处几个参数是否准确
        sysUser.setLoginCount(sysUser.getLoginCount()+1);
        if(BasisUtil.isNotEmpty(sysUser.getLastLoginTime())){
            if(!sysUser.getLastLoginTime().toString().substring(0,10)
                    .equalsIgnoreCase(LocalDateTime.now().toString().substring(0,10))){// 判断上一次登录时间是当前时间的前一天或更多天则证明今天没登陆过
                sysUser.setTodayLoginCount(1);
            }else{
                sysUser.setTodayLoginCount(sysUser.getTodayLoginCount()+1);
            }
        }else{
            sysUser.setTodayLoginCount(1);
        }
        if(BasisUtil.isEmpty(sysUser.getFirstLoginTime())){
            sysUser.setFirstLoginTime(LocalDateTime.now());
        }
        sysUser.setLastLoginTime(LocalDateTime.now());
        this.baseMapper.updateById(sysUser);
    }

    @Override
    public Set<String> getUserRoles(String loginName) {
        List<String> rolesList = sysRoleMapper.getUserRoles(loginName);
        return new HashSet<String>(rolesList);
    }

    @Override
    public Set<String> getUserPermissions(String loginName) {
        Set<String> permissionSet = new HashSet<String>();
        List<SysPermission> permissionList = sysPermissionMapper.queryByUser(loginName);
        for (SysPermission po : permissionList) {
            if (BasisUtil.isNotEmpty(po.getPerms())) {
                permissionSet.add(po.getPerms());
            }
        }
        return permissionSet;
    }
}
