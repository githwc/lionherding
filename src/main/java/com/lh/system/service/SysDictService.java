package com.lh.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lh.common.tree.TreeNode;
import com.lh.system.entity.SysDict;
import com.lh.system.model.query.DictQuery;

import java.util.List;

/**
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author xieyc
 * @Date 2019-09-20
 * @Version: 1.0.0
 *
 */
public interface SysDictService extends IService<SysDict> {

    /**
     * 查询所有字典,并以树结构格式返回前端
     * @param keyWord [搜索条件] 字典名称
     * @return tree
     */
    List<TreeNode> dictTree(String keyWord);

    /**
     * 查询子级字典
     * @param page 分页信息
     * @param dictQuery 父级字典ID
     * @return deptList
     */
    Page<SysDict> childrenDict(Page<SysDict> page, DictQuery dictQuery);

    /**
     * 根据字典Id修改
     * @param sysDict
     */
    void editByDictId(SysDict sysDict);

    /**
     * 根据字典ID删除
     * @param id
     */
    void deleteAlone(String id);

    /**
     * 字典批量删除
     * @param ids
     */
    void deleteBatch(String ids);

    /**
     * 创建字典
     * @param sysDict
     */
    void create(SysDict sysDict);

}
