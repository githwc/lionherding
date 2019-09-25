package com.lh.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 功能描述：
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2019-09-19
 * @Version: 1.0.0
 *
 *  mybatisPlus会默认使用实体类的类名到数据库中找对应的表
 *  也可以通过@TableName(value = "sys_user")来指定在数据库中的名字
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * @TableId:
     *  value:指定数据库表中主键的列名，如果实体类属性和表中字段一致，可省略
     *  type:指定主键策略
     *         IdType.AUTO：数据库ID自增
     *         IdType.INPUT: 用户输入ID
     *         IdType.ID_WORKER:  全局唯一ID，内容为空自动填充（默认配置）
     *         IdType.UUID: 全局唯一ID，内容为空自动填充
     */
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
    private Integer todayLoginCount;
    /**
     * 首次登录时间
     */
    private LocalDateTime firstLoginTime;
    /**
     * 最后一次登录时间
     */
    private LocalDateTime lastLoginTime;
    /**
     * 创建人
     */
    private String createId;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 修改人
     */
    private String updateId;
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
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

    /**
     * 通过此注解表明不对应数据库中的字段
     */
    @TableField(exist = false)
    private String tempArgs;
}
