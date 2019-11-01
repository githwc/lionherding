package com.lh.system.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lh.system.entity.SysRole;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2019-09-19
 * @Version: 1.0.0
 *
 */
public interface SysRoleService extends IService<SysRole> {

    Set<String> getUserRoles(String loginName);

    List<SysRole> roleList();

    Page<SysRole> queryPageAll(Page<SysRole> page, JSONObject jsonObject);

    void duplicate(String roleCode);

    Map<String,Object> queryTreeList();
}
