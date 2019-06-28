package com.lh.system.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
 * 功能描述：日志管理    SYS_Log
 * <p>
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author: 牧狮&&紫色年华
 * @Datetime: 2019-05-10 16:48
 */
@Data
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;			// UUID

    private String loginName;	// 登录名

    private String userId;		// 用户标识

    private int opType;			// 操作类型  0:增 1：删  2：改 3：读。

    private Date opDate;		// 操作时间

    private String opDesc;	    // 操作描述

    private String ipAdress;	// 操作IP地址

    private String functionId;	// 功能模块ID	(UUID)
}