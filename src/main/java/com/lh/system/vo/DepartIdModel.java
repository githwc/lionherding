package com.lh.system.vo;

import com.lh.system.entity.SysDept;
import lombok.Data;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 功能描述：【VO】部门表 封装树结构的部门的名称的实体类
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2019-10-26
 * @Version: 1.0.0
 */
@Data
public class DepartIdModel implements Serializable {

    private static final long serialVersionUID = 1L;

    // 主键ID
    private String key;

    // 主键ID
    private String value;

    // 部门名称
    private String title;

    List<DepartIdModel> children = new ArrayList<>();

    /**
     * 将SysDepartTreeModel的部分数据放在该对象当中
     * @param treeModel
     * @return
     */
    public DepartIdModel convert(SysDeptTree treeModel) {
        this.key = treeModel.getId();
        this.value = treeModel.getId();
        this.title = treeModel.getDepartName();
        return this;
    }

    /**
     * 该方法为用户部门的实现类所使用
     * @param sysDepart
     * @return
     */
    public DepartIdModel convertByUserDepart(SysDept sysDepart) {
        this.key = sysDepart.getSysDeptId();
        this.value = sysDepart.getSysDeptId();
        this.title = sysDepart.getDepartName();
        return this;
    }

}
