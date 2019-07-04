package com.lh.system.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 功能描述：
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author 牧狮&&紫色年华
 * @Date 2019-07-04
 * @Version: 1.0.0
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RoleMember implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;	// UUID

    private String roleId;	// 角色ID

    private String userId;	// 成员ID

}