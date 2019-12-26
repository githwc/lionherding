package com.lh.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 功能描述：部门
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author xieyc
 * @Date 2019-09-20
 * @Version: 1.0.0
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysDept implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "sys_dept_id", type = IdType.UUID)
    private String sysDeptId;
    /**
     * 父级ID
     */
    private String parentId;
    /**
     * 部门名称
     */
    private String departName;
    /**
     * 简称
     */
    private String shortName;
    /**
     * 负责人
     */
    private String managerId;
    /**
     * 固话
     */
    private String telephone;
    /**
     * 传真
     */
    private String fax;
    /**
     * 地址
     */
    private String address;
    /**
     * 机构类型(1.公司 2.部门 3 岗位)
     */
    private String orgType;
    /**
     * 唯一编码
     */
    private String uniqueCoding;
    /**
     * 层级
     */
    private Integer adminLevel;
    /**
     * 状态(0启用 1不启用)
     */
    private Integer state;
    /**
     * 删除状态(0:未删除 1:已删除)
     */
    private Integer delFlag;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 描述
     */
    private String description;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建人
     */
    private String createUserId;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 修改人
     */
    private String updateUserId;
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

}
