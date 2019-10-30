package com.lh.system.service.impl;

import com.lh.system.entity.SysRole;
import com.lh.system.entity.SysUser;
import com.lh.system.entity.SysUserRole;
import com.lh.system.mapper.SysUserRoleMapper;
import com.lh.system.service.SysRoleService;
import com.lh.system.service.SysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lh.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

/**
* 功能描述：
*
*  <p>版权所有：</p>
*  未经本人许可，不得以任何方式复制或使用本程序任何部分
*
* @Company: LionHerding
* @Author xieyc && 紫色年华
* @Date 2019-09-19
* @Version: 1.0.0
*
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysRoleService roleService;

    @Override
    public Map<String, String> queryUserRole() {
        List<SysUserRole> uRoleList = this.list();
        List<SysUser> userList = userService.list();
        List<SysRole> roleList = roleService.list();
        Map<String,String> map = new IdentityHashMap<>();
        String userId = "";
        String roleId = "";
        String roleName = "";
        if(uRoleList != null && uRoleList.size() > 0) {
            for(SysUserRole uRole : uRoleList) {
                roleId = uRole.getRoleId();
                for(SysUser user : userList) {
                    userId = user.getSysUserId();
                    if(uRole.getUserId().equals(userId)) {
                        roleName = this.searchByRoleId(roleList,roleId);
                        map.put(userId, roleName);
                    }
                }
            }
            return map;
        }
        return map;
    }

    /**
     * queryUserRole调用的方法
     * @param roleList
     * @param roleId
     * @return
     */
    private String searchByRoleId(List<SysRole> roleList, String roleId) {
        while(true) {
            for(SysRole role : roleList) {
                if(roleId.equals(role.getSysRoleId())) {
                    return role.getRoleName();
                }
            }
        }
    }
}
