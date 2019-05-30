package com.lh.system.model;

import java.util.Date;

/**
 * 功能描述：Model-角色管理 sys_Role
 * <p>
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author: 牧狮&&紫色年华
 * @Datetime: 2019-05-10 16:57
 */
public class Role {

    private String id;			// UUID

    private String name;		// 角色名称

    private int state;			// 状态 (0：开放、1：禁止)

    private int sort;			// 排序

    private String remark;		// 备注说明

    private String createName;	// 创建人姓名

    private String createId;	// 创建人标识

    private Date createDate;	// 创建日期
}
