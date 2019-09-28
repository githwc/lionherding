package com.lh.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lh.system.entity.SysUser;

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

    /**
     * 根据账号查询用户
     * @param loginName
     * @return
     */
    SysUser getUserByName(String loginName);

    /**
     * 处理用户数据
     */
    void dealUser(SysUser sysUser);

}
