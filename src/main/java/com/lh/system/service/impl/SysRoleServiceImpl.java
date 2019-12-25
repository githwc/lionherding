package com.lh.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lh.common.config.exception.RunException.RunningException;
import com.lh.common.constant.CommonConstant;
import com.lh.system.entity.SysPermission;
import com.lh.system.entity.SysRole;
import com.lh.system.mapper.SysRoleMapper;
import com.lh.system.service.SysPermissionService;
import com.lh.system.service.SysRoleService;
import com.lh.system.vo.TreeModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 功能描述：
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2019-09-19
 * @Version: 1.0.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysPermissionService sysPermissionService;

    @Override
    public Set<String> getUserRoles(String loginName) {
        Set<String> roleSet = new HashSet<String>();
        List<SysRole> rolesList = this.baseMapper.getUserRoles(loginName);
        for (SysRole po : rolesList) {
            if (StringUtils.isNotEmpty(po.getRoleCode())) {
                roleSet.add(po.getRoleCode());
            }
        }
        return new HashSet<String>(roleSet);
    }

    @Override
    public List<SysRole> roleList() {
        return this.baseMapper.selectList(new LambdaQueryWrapper<SysRole>()
                .eq(SysRole::getDelFlag, CommonConstant.DEL_FLAG_0)
                .orderByAsc(SysRole::getSort)
        );
    }

    @Override
    public Page<SysRole> queryPageAll(Page<SysRole> page, JSONObject jsonObject) {
        return this.baseMapper.queryPageAll(page,jsonObject);
    }

    @Override
    public void duplicate(String roleCode) {
        List<SysRole> list = this.baseMapper.selectList(new LambdaQueryWrapper<SysRole>()
            .eq(SysRole::getRoleCode,roleCode)
                .eq(SysRole::getDelFlag,CommonConstant.DEL_FLAG_0)
        );
        if(list != null && list.size() >0){
            throw new RunningException("该角色码已存在！");
        }
    }

    @Override
    public Map<String, Object> queryTreeList() {
        List<String> ids = new ArrayList<>();
        List<SysPermission> list = sysPermissionService.list(new LambdaQueryWrapper<SysPermission>()
                .eq(SysPermission::getDelFlag, CommonConstant.DEL_FLAG_0)
                .orderByAsc(SysPermission::getSort)
        );
        for(SysPermission sysPer : list) {
            ids.add(sysPer.getSysPermissionId());
        }
        List<TreeModel> treeList = new ArrayList<>();
        getTreeModelList(treeList, list, null);
        Map<String,Object> resMap = new HashMap<String,Object>();
        resMap.put("treeList", treeList); //全部树节点数据
        resMap.put("ids", ids);//全部树ids
        return resMap;
    }

    private void getTreeModelList(List<TreeModel> treeList,List<SysPermission> metaList,TreeModel temp) {
        for (SysPermission permission : metaList) {
            String tempPid = permission.getParentId();
            TreeModel tree = new TreeModel(permission.getSysPermissionId(), tempPid, permission.getName(),permission.getRuleFlag(), permission.getIsLeaf());
            if(temp==null && StringUtils.isEmpty(tempPid)) {
                treeList.add(tree);
                if(!tree.isLeaf()) {
                    getTreeModelList(treeList, metaList, tree);
                }
            }else if(temp!=null && tempPid!=null && tempPid.equals(temp.getKey())){
                temp.getChildren().add(tree);
                if(!tree.isLeaf()) {
                    getTreeModelList(treeList, metaList, tree);
                }
            }
        }
    }


}
