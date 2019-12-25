package com.lh.system.utils;

import com.alibaba.druid.util.StringUtils;
import com.lh.system.entity.SysDept;
import com.lh.system.vo.DepartIdModel;
import com.lh.system.vo.SysDeptTree;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：部门操作工具类
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2019-10-26
 * @Version: 1.0.0
 */
public class DeptOPUtil {

    /**
     * @DESC：将SysDept类型的list集合转换成SysDeptTree类型的集合
     *  【queryTreeList的子方法 ====1=====】
     * @param recordList
     * @return
     */
    public static List<SysDeptTree> deptToTree(List<SysDept> recordList) {
        List<DepartIdModel> idList = new ArrayList<DepartIdModel>();
        List<SysDeptTree> records = new ArrayList<>();
        for (int i = 0; i < recordList.size(); i++) {
            SysDept depart = recordList.get(i);
            records.add(new SysDeptTree(depart));
        }
        List<SysDeptTree> tree = findChildren(records, idList);
        setEmptyChildrenAsNull(tree);
        return tree;
    }

    /**
     * @DESC:找到并封装顶级父类的节点到TreeList集合
     *  【queryTreeList的子方法 ====2=====】
     * @param recordList
     * @param departIdList
     * @return
     */
    private static List<SysDeptTree> findChildren(List<SysDeptTree> recordList,
                                                         List<DepartIdModel> departIdList) {
        List<SysDeptTree> treeList = new ArrayList<>();
        for (int i = 0; i < recordList.size(); i++) {
            SysDeptTree branch = recordList.get(i);
            if (StringUtils.isEmpty(branch.getParentId())) {
                treeList.add(branch);
                DepartIdModel departIdModel = new DepartIdModel().convert(branch);
                departIdList.add(departIdModel);
            }
        }
        findALlChildren(treeList,recordList,departIdList);
        return treeList;
    }


    /**
     * @DESC:找到顶级父类下的所有子节点集合并封装到TreeList集合
     *  【queryTreeList的子方法====3====】
     * @param treeList
     * @param recordList
     * @param idList
     */
    private static void findALlChildren(List<SysDeptTree> treeList,List<SysDeptTree> recordList,List<DepartIdModel> idList) {
        for (int i = 0; i < treeList.size(); i++) {
            SysDeptTree model = treeList.get(i);
            DepartIdModel idModel = idList.get(i);
            for (int i1 = 0; i1 < recordList.size(); i1++) {
                SysDeptTree m = recordList.get(i1);
                if (m.getParentId()!=null && m.getParentId().equals(model.getId())) {
                    model.getChildren().add(m);
                    DepartIdModel dim = new DepartIdModel().convert(m);
                    idModel.getChildren().add(dim);
                }
            }
            findALlChildren(treeList.get(i).getChildren(), recordList, idList.get(i).getChildren());
        }
    }


    /**
     * @DESC:将子节点为空的List集合设置为Null值
     *  【queryTreeList的子方法 ====4====】
     *
     * @param treeList
     */
    private static void setEmptyChildrenAsNull(List<SysDeptTree> treeList) {
        for (int i = 0; i < treeList.size(); i++) {
            SysDeptTree model = treeList.get(i);
            if (model.getChildren().size() == 0) {
                model.setChildren(null);
                model.setLeaf(true);
            }else{
                setEmptyChildrenAsNull(model.getChildren());
                model.setLeaf(false);
            }
        }
    }


    /**
     * 获取 DepartIdModel
     * @param recordList
     * @return
     */
    public static List<DepartIdModel> wrapTreeDataToDepartIdTreeList(List<SysDept> recordList) {
        List<DepartIdModel> idList = new ArrayList<DepartIdModel>();
        List<SysDeptTree> records = new ArrayList<>();
        for (int i = 0; i < recordList.size(); i++) {
            SysDept depart = recordList.get(i);
            records.add(new SysDeptTree(depart));
        }
        findChildren(records, idList);
        return idList;
    }

}
