package com.lh.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 功能描述：
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author 牧狮&&紫色年华
 * @Date 2019-09-19
 * @Version: 1.0.0
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "sys_user_id", type = IdType.UUID)
    private String sysUserId;
    /**
     * 真实姓名
     */
    private String userName;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 登录名称
     */
    private String loginName;
    /**
     * 密码
     */
    private String password;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 职务
     */
    private Integer jobs;
    /**
     * 性别(0女 1男)
     */
    private Integer sex;
    /**
     * 生日
     */
    private LocalDateTime birthday;
    /**
     * 头像
     */
    private String picture;
    /**
     * 身份证
     */
    private String cardId;
    /**
     * 移动电话
     */
    private String mobilePhone;
    /**
     * 固话
     */
    private String telephone;
    /**
     * 短号
     */
    private String shortTel;
    /**
     * 传真
     */
    private String fax;
    /**
     * 住址
     */
    private String address;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 登录次数
     */
    private Integer loginCount;
    /**
     * 今日登录次数
     */
    private Integer toDayLoginCount;
    /**
     * 创建人
     */
    private String createId;
    /**
     * 创建时间
     */
    private LocalDateTime createDate;
    /**
     * 建人
     */
    private String updateId;
    private LocalDateTime updateDate;
    /**
     * 序号
     */
    private Integer sort;
    /**
     * 状态(0正常 1 冻结 2 已删除)
     */
    private Integer state;
    /**
     * 备注
     */
    private String remark;
    /**
     * 所属部门
     */
    private String departId;


    /////////////////////////////// 非表字段 ///////////////////////////////

}
