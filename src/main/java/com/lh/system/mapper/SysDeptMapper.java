package com.lh.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lh.common.tree.TreeNode;
import com.lh.system.entity.SysDept;
import com.lh.system.model.vo.SysDeptVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2019-09-20
 * @Version: 1.0.0
 *
 */
@Repository
public interface SysDeptMapper extends BaseMapper<SysDept> {

    /**
     * 部门树
     * @return tree
     */
    List<TreeNode> queryTreeList();

    /**
     * 子级部门
     * @return dept list
     */
    Page<SysDept> childrenDept(@Param("page")Page<SysDeptVO> page,@Param("parentId")String parentId);
}
