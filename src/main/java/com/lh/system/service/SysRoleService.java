package com.lh.system.service;

import com.lh.system.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

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
public interface SysRoleService extends IService<SysRole> {

    boolean insertOrUpdate(SysRole iSysRole);

}
