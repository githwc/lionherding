package com.lh.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lh.common.utils.BasisUtil;
import com.lh.system.entity.SysPermission;
import com.lh.system.mapper.SysPermissionMapper;
import com.lh.system.service.SysPermissionService;
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
* @Company: 紫色年华
* @Author xieyc
* @Date 2019-09-20
* @Version: 1.0.0
*
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    @Override
    public Set<String> getUserPermissions(String loginName) {
        Set<String> permissionSet = new HashSet<String>();
        List<SysPermission> permissionList = this.baseMapper.queryPermissionByUser(loginName);
        for (SysPermission po : permissionList) {
            if (BasisUtil.isNotEmpty(po.getPerms())) {
                permissionSet.add(po.getPerms());
            }
        }
        return permissionSet;
    }

    @Override
    public List<SysPermission> queryPermissionByUser(String loginName) {
        return this.baseMapper.queryPermissionByUser(loginName);
    }

    @Override
    public List<SysPermission> queryPermissionByArgs(Integer delFlag,Integer menuType) {
        // return this.baseMapper.selectList(new QueryWrapper<SysPermission>()
        //     .eq("del_flag",delFlag)
        //         .eq("menu_type",menuType)
        // );
        return this.baseMapper.getALlPermission();
        // return null;
    }

}
