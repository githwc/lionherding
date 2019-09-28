package com.lh.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lh.common.utils.BasisUtil;
import com.lh.system.entity.SysRole;
import com.lh.system.mapper.SysRoleMapper;
import com.lh.system.service.SysRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 功能描述：
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2019-09-19
 * @Version: 1.0.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Override
    public Set<String> getUserRoles(String loginName) {
        Set<String> roleSet = new HashSet<String>();
        List<SysRole> rolesList = this.baseMapper.getUserRoles(loginName);
        for (SysRole po : rolesList) {
            if (BasisUtil.isNotEmpty(po.getRoleCode())) {
                roleSet.add(po.getRoleCode());
            }
        }
        return new HashSet<String>(roleSet);
    }
}
