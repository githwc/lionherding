package com.lh.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lh.common.config.exception.RunException.RunningException;
import com.lh.common.config.exception.parameterException.ParameterException;
import com.lh.common.constant.CommonConstant;
import com.lh.common.dao.DaoApi;
import com.lh.common.tree.Tree;
import com.lh.common.tree.TreeNode;
import com.lh.system.entity.SysDict;
import com.lh.system.mapper.SysDictMapper;
import com.lh.system.model.query.DictQuery;
import com.lh.system.service.SysDictService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
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
@Service
@Transactional(rollbackFor = Exception.class)
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {

    private final DaoApi daoApi;

    @Autowired
    public SysDictServiceImpl(DaoApi daoApi) {
        this.daoApi = daoApi;
    }

    @Override
    public List<TreeNode> dictTree(String keyWord) {
        List<TreeNode> list = this.baseMapper.dictTree(keyWord);
        Tree tree = new Tree(list).setRoot("字典管理").build();
        return tree.getRootNodes();
    }

    @Override
    public Page<SysDict> childrenDict(Page<SysDict> page, DictQuery dictQuery) {
        return this.baseMapper.childrenDict(page,dictQuery);
    }

    @Override
    public void editByDictId(SysDict sysDict) {
        sysDict.setUpdateUserId(daoApi.getCurrentUserId());
        this.updateById(sysDict);
    }

    @Override
    public void deleteAlone(String id) {
        List<String> idList = new ArrayList<String>();
        SysDict sysDict = this.getById(id);
        if(sysDict == null) {
            throw new RunningException("未找到对应实体");
        }else {
            //逻辑删除子级字典
            idList.add(id);
            this.checkChildrenExists(id, idList);
            idList.forEach(currId->{
                SysDict sysDict1 = new SysDict();
                sysDict1.setDelFlag(CommonConstant.DEL_FLAG_1);
                this.update(sysDict1,new LambdaQueryWrapper<SysDict>()
                        .eq(SysDict::getSysDictId,currId)
                );
            });
        }
    }

    @Override
    public void deleteBatch(String ids) {
        if (ids == null || "".equals(ids.trim())) {
            throw new ParameterException("参数不识别！");
        } else {
            List<String> list = Arrays.asList(ids.split(","));
            list.forEach(this::deleteAlone);
        }
    }

    @Override
    public void create(SysDict sysDict) {
        if (sysDict != null ) {
            sysDict.setCreateUserId(daoApi.getCurrentUserId());
            if (StringUtils.isBlank(sysDict.getParentId())) {
                sysDict.setParentId("#");
            }
            this.save(sysDict);
        }
    }

    /**
     *  查询子级字典  ====== deleteAlone 子方法 =======
     * @param id 字典ID
     * @param idList idList
     */
    private void checkChildrenExists(String id, List<String> idList) {
        List<SysDict> dictList = this.list(new LambdaQueryWrapper<SysDict>()
                .eq(SysDict::getParentId,id)
                .eq(SysDict::getDelFlag,CommonConstant.DEL_FLAG_0)
        );
        if(dictList != null && dictList.size() > 0) {
            for(SysDict dict : dictList) {
                idList.add(dict.getSysDictId());
                this.checkChildrenExists(dict.getSysDictId(), idList);
            }
        }
    }

}
