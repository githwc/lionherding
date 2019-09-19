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
 * @Author xieyc && 紫色年华
 * @Date 2019-09-20
 * @Version: 1.0.0
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键id
     */
    @TableId(value = "sys_permission_id", type = IdType.UUID)
    private String sysPermissionId;
    /**
     * 父id
     */
    private String parentId;
    /**
     * 菜单标题
     */
    private String name;
    /**
     * 路径
     */
    private String url;
    /**
     * 组件
     */
    private String component;
    /**
     * 组件名字
     */
    private String componentName;
    /**
     * 一级菜单跳转地址
     */
    private String redirect;
    /**
     * 菜单类型(0:一级菜单; 1:子菜单:2:按钮权限)
     */
    private Integer menuType;
    /**
     * 菜单权限编码
     */
    private String perms;
    /**
     * 权限策略1显示2禁用
     */
    private String permsType;
    /**
     * 菜单排序
     */
    private Integer sortNo;
    /**
     * 聚合子路由: 1是0否
     */
    private Boolean alwaysShow;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 是否路由菜单: 0:不是  1:是（默认值1）
     */
    private Boolean isRoute;
    /**
     * 是否叶子节点:    1:是   0:不是
     */
    private Boolean isLeaf;
    /**
     * 是否隐藏路由: 0否,1是
     */
    private Integer hidden;
    /**
     * 描述
     */
    private String description;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 删除状态 0正常 1已删除
     */
    private Integer delFlag;
    /**
     * 是否添加数据权限1是0否
     */
    private Integer ruleFlag;
    /**
     * 按钮权限状态(0无效1有效)
     */
    private String status;


    /////////////////////////////// 非表字段 ///////////////////////////////

}