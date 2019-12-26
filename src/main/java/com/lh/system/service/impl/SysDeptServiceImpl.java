package com.lh.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lh.common.config.exception.RunException.RunningException;
import com.lh.common.config.exception.parameterException.ParameterException;
import com.lh.common.constant.CacheConstant;
import com.lh.common.constant.CommonConstant;
import com.lh.common.dao.DaoApi;
import com.lh.common.tree.TreeNode;
import com.lh.common.utils.YouBianCodeUtil;
import com.lh.system.entity.SysDept;
import com.lh.system.mapper.SysDeptMapper;
import com.lh.system.service.SysDeptService;
import com.lh.system.utils.DeptOPUtil;
import com.lh.system.vo.DepartIdModel;
import com.lh.system.vo.SysDeptTree;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
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
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

    @Autowired
    private DaoApi daoApi;

    @Override
    public List<SysDeptTree> queryTreeList() {
        List<SysDept> list = this.list(new LambdaQueryWrapper<SysDept>()
                .eq(SysDept::getDelFlag,CommonConstant.DEL_FLAG_0)
                .orderByAsc(SysDept::getSort)
        );
        return DeptOPUtil.deptToTree(list);
    }

    @Override
    public List<TreeNode> queryTreeList2() {
        return this.baseMapper.queryTreeList2();
    }

    @Override
    public List<SysDeptTree> searchBy(String keyWord) {
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
        sysDept.setUpdateUserId(daoApi.getCurrentUserId());
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

    @Override
    public void deleteBatch(String ids) {
        if (ids == null || "".equals(ids.trim())) {
            throw new ParameterException("参数不识别！");
        } else {
            List<String> list = Arrays.asList(ids.split(","));
            list.forEach(curr->{
                this.deleteById(curr);
            });
        }
    }

    @Override
    @CacheEvict(value= {CacheConstant.SYS_DEPARTS_CACHE,CacheConstant.SYS_DEPART_IDS_CACHE}, allEntries=true)
    public void create(SysDept sysDept, HttpServletRequest request) {
        if (sysDept != null ) {
            if (sysDept.getParentId() == null) {
                sysDept.setParentId("");
            }
            String parentId = sysDept.getParentId();
            String[] codeArray = generateOrgCode(parentId);
            sysDept.setUniqueCoding(codeArray[0]);
            String orgType = codeArray[1];
            sysDept.setOrgType(String.valueOf(orgType));
            sysDept.setCreateUserId(daoApi.getCurrentUserId());
            this.save(sysDept);
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

    /**
     * 生成部门编码和部门类型
     *
     * @param parentId
     * @return
     */
    private String[] generateOrgCode(String parentId) {
        String[] strArray = new String[2];
        // 创建一个List集合,存储查询返回的所有SysDepart对象
        List<SysDept> departList = new ArrayList<>();
        // 定义新编码字符串
        String newOrgCode = "";
        // 定义旧编码字符串
        String oldOrgCode = "";
        // 定义部门类型
        String orgType = "";
        // 如果是最高级,则查询出同级的org_code, 调用工具类生成编码并返回
        if (StringUtil.isNullOrEmpty(parentId)) {
            // 先判断数据库中的表是否为空,空则直接返回初始编码
            departList = this.list(new LambdaQueryWrapper<SysDept>()
                    .eq(SysDept::getParentId, "").or().isNull(SysDept::getParentId)
                    .orderByDesc(SysDept::getUniqueCoding)
            );
            if(departList == null || departList.size() == 0) {
                strArray[0] = YouBianCodeUtil.getNextYouBianCode(null);
                strArray[1] = "1";
                return strArray;
            }else {
                SysDept depart = departList.get(0);
                oldOrgCode = depart.getUniqueCoding();
                orgType = depart.getOrgType();
                newOrgCode = YouBianCodeUtil.getNextYouBianCode(oldOrgCode);
            }
        } else { // 反之则查询出所有同级的部门,获取结果后有两种情况,有同级和没有同级
            // 查询出同级部门的集合
            List<SysDept> parentList = this.list(new LambdaQueryWrapper<SysDept>()
                .eq(SysDept::getParentId,parentId)
                    .orderByDesc(SysDept::getUniqueCoding)
            );
            // 查询出父级部门
            SysDept depart = this.getById(parentId);
            // 获取父级部门的Code
            String parentCode = depart.getUniqueCoding();
            // 根据父级部门类型算出当前部门的类型
            orgType = String.valueOf(Integer.valueOf(depart.getOrgType()) + 1);
            // 处理同级部门为null的情况
            if (parentList == null || parentList.size() == 0) {
                // 直接生成当前的部门编码并返回
                newOrgCode = YouBianCodeUtil.getSubYouBianCode(parentCode, null);
            } else { //处理有同级部门的情况
                // 获取同级部门的编码,利用工具类
                String subCode = parentList.get(0).getUniqueCoding();
                // 返回生成的当前部门编码
                newOrgCode = YouBianCodeUtil.getSubYouBianCode(parentCode, subCode);
            }
        }
        // 返回最终封装了部门编码和部门类型的数组
        strArray[0] = newOrgCode;
        strArray[1] = orgType;
        return strArray;
    }
}
