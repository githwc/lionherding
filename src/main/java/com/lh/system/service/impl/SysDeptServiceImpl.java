package com.lh.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lh.common.config.exception.userException.RunningException;
import com.lh.common.constant.CacheConstant;
import com.lh.common.constant.CommonConstant;
import com.lh.system.entity.SysDept;
import com.lh.system.mapper.SysDeptMapper;
import com.lh.system.service.SysDeptService;
import com.lh.system.utils.DeptOPUtil;
import com.lh.system.vo.DepartIdModel;
import com.lh.system.vo.SysDeptTree;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable(value = CacheConstant.SYS_DEPART_IDS_CACHE)
    @Override
    public List<DepartIdModel> queryDepartIdTreeList() {
        List<SysDept> list = this.list(new LambdaQueryWrapper<SysDept>()
            .eq(SysDept::getDelFlag,CommonConstant.DEL_FLAG_0)
                .orderByAsc(SysDept::getSort)
        );
        return DeptOPUtil.wrapTreeDataToDepartIdTreeList(list);
    }

    @Override
    @CacheEvict(value= {CacheConstant.SYS_DEPARTS_CACHE,CacheConstant.SYS_DEPART_IDS_CACHE}, allEntries=true)
    public void editByDeptId(SysDept sysDept) {
        // TODO: 2019/10/30 修改人
        // sysDept.setUpdateUserId(username);
        this.updateById(sysDept);
    }

    @Override
    @CacheEvict(value= {CacheConstant.SYS_DEPARTS_CACHE,CacheConstant.SYS_DEPART_IDS_CACHE}, allEntries=true)
    public void deleteById(String id) {
        List<String> idList = new ArrayList<String>();
        SysDept sysDept = this.getById(id);
        if(sysDept == null) {
            throw new RunningException("未找到对应实体");
        }else {
            //逻辑删除子部门
            idList.add(id);
            this.checkChildrenExists(id, idList);
            idList.forEach(currId->{
                SysDept sysDept1 = new SysDept();
                sysDept1.setDelFlag(CommonConstant.DEL_FLAG_1);
                this.update(sysDept1,new LambdaQueryWrapper<SysDept>()
                        .eq(SysDept::getSysDeptId,currId)
                );
            });
        }
    }

    /**
     * 查找子部门
     * @param id
     * @param idList
     */
    private void checkChildrenExists(String id, List<String> idList) {
        List<SysDept> departList = this.list(new LambdaQueryWrapper<SysDept>()
                .eq(SysDept::getParentId,id)
                .eq(SysDept::getDelFlag,CommonConstant.DEL_FLAG_0)
        );
        if(departList != null && departList.size() > 0) {
            for(SysDept depart : departList) {
                idList.add(depart.getSysDeptId());
                this.checkChildrenExists(depart.getSysDeptId(), idList);
            }
        }
    }
}
