package com.lh.system.model;

import lombok.Data;
/**
 * 功能描述：Model - 角色成员管理  SYS_RoleMember
 * <p>
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author: 牧狮&&紫色年华
 * @Datetime: 2019-05-10 17:02
 */
@Data
public class RoleMember {
    private String id;	// UUID

    private String roleId;	// 角色ID

    private String userId;	// 成员ID

}