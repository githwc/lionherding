package com.lh.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

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
@TableName(value = "sys_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;			// UUID

    private String name;		// 角色名称

    private int state;			// 状态 (0：开放、1：禁止)

    private int sort;			// 排序

    private String remark;		// 备注说明

    private String createName;	// 创建人姓名

    private String createId;	// 创建人标识

    private Date createDate;	// 创建日期
}