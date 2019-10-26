package com.lh.system.vo;

import com.lh.system.entity.SysDept;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：部门树VO类
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2019-10-26
 * @Version: 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysDeptTree implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 对应dept中的id字段,前端数据树中的key
     */
    private String key;

    /**
     * 对应dept中的id字段,前端数据树中的value
     */
    private String value;

    /**
     * 对应depart_name字段,前端数据树中的title
     */
    private String title;

    private boolean isLeaf;

    /* 以下所有字段均与dept相同 */
    private String id;

    private String parentId;

    private String departName;

    private String shortName;

    private Integer sort;

    private Object description;

    private String orgType;

    private String uniqueCoding;

    private String telephone;

    private String fax;

    private String address;

    private String remark;

    private Integer state;

    private String createUserId;

    private LocalDateTime createTime;

    private String updateUserId;

    private LocalDateTime updateTime;

    private List<SysDeptTree> children = new ArrayList<>();

    public SysDeptTree() { }

    /**
     * 将dept对象转换成deptTreeModel对象
     * @param dept
     */
    public SysDeptTree(SysDept dept) {
        this.key = dept.getSysDeptId();
        this.value = dept.getSysDeptId();
        this.title = dept.getDepartName();
        this.id = dept.getSysDeptId();
        this.parentId = dept.getParentId();
        this.departName = dept.getDepartName();
        this.shortName = dept.getShortName();
        this.sort = dept.getSort();
        this.description = dept.getDescription();
        this.orgType = dept.getOrgType();
        this.uniqueCoding = dept.getUniqueCoding();
        this.telephone = dept.getTelephone();
        this.fax = dept.getFax();
        this.address = dept.getAddress();
        this.remark = dept.getRemark();
        this.state = dept.getState();
        this.createUserId = dept.getCreateUserId();
        this.createTime = dept.getCreateTime();
        this.updateUserId = dept.getUpdateUserId();
        this.updateTime = dept.getUpdateTime();
    }

}
