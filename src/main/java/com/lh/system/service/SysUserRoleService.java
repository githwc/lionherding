package com.lh.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lh.system.entity.SysUserRole;

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
public interface SysUserRoleService extends IService<SysUserRole> {

    /**
     * 查询用户所对应的角色信息
     *
     * @return
     */
    Map<String,String> queryUserRole();
}
