package com.lh.system.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 功能描述：Model - 部门管理    SYS_Depart
 * <p>
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author: 牧狮&&紫色年华
 * @Datetime: 2019-05-10 16:14
 */

@Data
public class Depart implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;					// UUID

    private String parentId;			// 父级部门ID

    private String name;				// 部门名称

    private String shortName;			// 部门简称

    private String managerId;           //负责人ID

    private String managerName;			// 负责人

    private String telephone;			// 电话

    private String address;				// 地址

    private String zipCode;				// 邮编

    private String orgDomain; 			// 组织域名

    private int nodeType;				// 组织类型，包含：区域、处室或科室、单位或部门

    private String uniqueCoding;		// 组织唯一编码

    private int adminLevel;				// 行政等级

    private int state;					// 状态(0.有效 1.删除 )

    private String createId;			// 创建人标识（UUID）

    private String createName;	        // 创建人姓名

    private Date createDate;			// 创建日期

    private int sort;					// 排序

    private String remark;				// 备注

}