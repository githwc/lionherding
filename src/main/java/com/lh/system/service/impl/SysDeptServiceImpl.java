package com.lh.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lh.common.constant.CommonConstant;
import com.lh.system.entity.SysDept;
import com.lh.system.mapper.SysDeptMapper;
import com.lh.system.service.SysDeptService;
import com.lh.system.utils.DeptOPUtil;
import com.lh.system.vo.SysDeptTree;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

    @Override
    public List<SysDeptTree> queryTreeList() {
        List<SysDept> list = this.list(new LambdaQueryWrapper<SysDept>()
                .eq(SysDept::getDelFlag,CommonConstant.DEL_FLAG_0)
                .orderByAsc(SysDept::getSort)
        );
        return DeptOPUtil.deptToTree(list);
    }

    @Override
    public List<SysDeptTree> searhBy(String keyWord) {
        List<SysDept> list = this.list(new LambdaQueryWrapper<SysDept>()
                .like(SysDept::getDepartName,keyWord)
                .eq(SysDept::getDelFlag, CommonConstant.DEL_FLAG_0)
                .orderByAsc(SysDept::getSort)
        );
        List<SysDeptTree> newList = new ArrayList<>();
        if(list!= null && list.size()>0){
            SysDeptTree model = null;
            for(SysDept sysDept:list){
                model = new SysDeptTree(sysDept);
                model.setChildren(null);
                newList.add(model);
            }
            return newList;
        }
        return null;
    }

}
