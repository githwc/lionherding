package com.xyc.system.model;

import lombok.Data;
import java.util.Date;

/**
 * 功能描述：Model - 系统用户管理  SYS_User
 * <p>
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author: 牧狮&&紫色年华
 * @Datetime: 2019-05-10 15:20
 */
@Data
public class User {

    private String id;				// UUID

    private String name;			// 真实姓名

    private String nickName;		// 用户昵称

    private String loginName;		// 登陆名称

    private String password;		// 登陆密码

    private int jobs;				// 职务或岗位

    private int sex;				// 性别(0：未知性别、1：男性、 2：女性)

    private Date birthday;			// 出生年月

    private String picture;			// 个人头像路径

    private String cardId;			// 身份证号码

    private String mobilePhone;		// 手机号码

    private String telephone; 	    // 办公电话

    private String shortTel;		// 短号

    private String fax;				// 传真

    private String address;			// 家庭住址

    private String email;			// 邮箱地址

    private String ipAddress;		// 注册IP地址

    private Date regDate;			// 注册日期

    private int loginCount = 0;		// 登陆次数

    private int toDayLoginCount = 0;// 今日登陆次数

    private String createId;		// 创建人标识（UUID）

    private String createName;	    // 创建人姓名

    private Date createDate;		// 创建日期

    private int sort = 100;			// 排序

    private int state = 0;			// 用户状态 (1：有效、0：注销)

    private String remark;			// 备注说明

}
