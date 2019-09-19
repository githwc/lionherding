package com.lh.system.service.impl;

import com.lh.system.entity.SysRolePermission;
import com.lh.system.mapper.SysRolePermissionMapper;
import com.lh.system.service.SysRolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* 功能描述：
*
*  <p>版权所有：</p>
*  未经本人许可，不得以任何方式复制或使用本程序任何部分
*
* @Company: LionHerding
* @Author xieyc && 紫色年华
* @Date 2019-09-20
* @Version: 1.0.0
*
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService {

    @Override
    public boolean insertOrUpdate(SysRolePermission iSysRolePermission) {
        boolean result = false;
        return result;
    }
}
