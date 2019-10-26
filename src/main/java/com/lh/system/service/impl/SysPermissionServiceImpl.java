package com.lh.system.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lh.common.config.exception.parameterException.ParameterException;
import com.lh.common.config.filter.JwtUtil;
import com.lh.common.constant.CommonConstant;
import com.lh.common.utils.BasisUtil;
import com.lh.common.utils.EncoderUtil;
import com.lh.system.entity.SysPermission;
import com.lh.system.mapper.SysPermissionMapper;
import com.lh.system.service.SysPermissionService;
import com.lh.system.utils.PermissionOPUtil;
import com.lh.system.vo.SysPermissionTree;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
@Slf4j
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    @Override
    public Set<String> getUserPermCodes(String loginName) {
        Set<String> permissionSet = new HashSet<String>();
        List<SysPermission> permissionList = this.baseMapper.queryPermissionByUser(loginName);
        for (SysPermission po : permissionList) {
            if (BasisUtil.isNotEmpty(po.getPermsCode())) {
                permissionSet.add(po.getPermsCode());
            }
        }
        return permissionSet;
    }

    @Override
    public JSONObject getUserPermissionByToken(String token, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        if (BasisUtil.isEmpty(token)) {
            throw new ParameterException("参数错误:TOKEN不允许为空");
        }
        String loginName = JwtUtil.getUsername(token);
        List<SysPermission> metaList = this.baseMapper.queryPermissionByUser(loginName);
        PermissionOPUtil.addIndexPage(metaList);
        JSONArray menujsonArray = new JSONArray();
        this.getMenuJsonArray(menujsonArray, metaList, null);
        JSONArray authjsonArray = new JSONArray();
        this.getAuthJsonArray(authjsonArray, metaList);
        // 查询所有的权限
        List<SysPermission> allAuthList = this.baseMapper.selectList(new LambdaQueryWrapper<SysPermission>()
                .eq(SysPermission::getDelFlag,0)
                .eq(SysPermission::getMenuType,2)
                .eq(SysPermission::getStatus,"1")
        );
        JSONArray allauthjsonArray = new JSONArray();
        this.getAllAuthJsonArray(allauthjsonArray, allAuthList);
        json.put("menu", menujsonArray);
        json.put("auth", authjsonArray);
        json.put("allAuth", allauthjsonArray);
        return json;
    }

    /**
     *  @DESC：组装前端菜单JSON数组
     *  1、遍历第一条菜单权限信息，此时parentJson为null
     *  2、如果是一级菜单，执行@1(封装成json对象),执行@2(添加到jsonArray,继续调用getMenuJsonArray方法,并将完整metaList及封装的json对象传入)
     *      然后遍历所有metaList,如果是一级菜单不执行任何操作,如果是二级菜单并且此parentJson的ID等于当前菜单的父ID,
     *      执行@3(将子菜单组装到children中，将按钮组装到meta->permissionList中,如果不是叶子节点,会继续调用getMenuJsonArray方法封装其下级菜单)
     *  3、如果是二级菜单或三级菜单，不执行任何操作(在一级菜单走完后会循环放入二级三级菜单)
     *
     *   menuType: 类型( 0：一级菜单 1：子菜单 2：按钮 )
     * @param jsonArray 组装的json
     * @param metaList 当前人拥有的菜单权限
     * @param parentJson 父级菜单json对象
     */
    private void getMenuJsonArray(JSONArray jsonArray, List<SysPermission> metaList, JSONObject parentJson) {
        for (SysPermission permission : metaList){
            if (permission.getMenuType() == null){
                continue;
            }
            String tempPid = permission.getParentId();
            // @1
            JSONObject json = getMenuJsonObject(permission);
            if(json==null) {
                continue;
            }
            //@2
            if (parentJson == null && BasisUtil.isEmpty(tempPid)) {
                jsonArray.add(json);
                if (!permission.getIsLeaf()) {
                    getMenuJsonArray(jsonArray, metaList, json);
                }
                // @3
            } else if (parentJson != null && BasisUtil.isNotEmpty(tempPid) && tempPid.equals(parentJson.getString("id"))) {
                if (permission.getMenuType() == 2) {
                    JSONObject metaJson = parentJson.getJSONObject("meta");
                    if (metaJson.containsKey("permissionList")) {
                        metaJson.getJSONArray("permissionList").add(json);
                    } else {
                        JSONArray permissionList = new JSONArray();
                        permissionList.add(json);
                        metaJson.put("permissionList", permissionList);
                    }
                } else if (permission.getMenuType() == 1 || permission.getMenuType() == 0) {
                    if (parentJson.containsKey("children")) {
                        parentJson.getJSONArray("children").add(json);
                    } else {
                        JSONArray children = new JSONArray();
                        children.add(json);
                        parentJson.put("children", children);
                    }

                    if (!permission.getIsLeaf()) {
                        getMenuJsonArray(jsonArray, metaList, json);
                    }
                }
            }
        }
    }

    /**
     * @DESC：单条权限信息拼装JSON对象
     *  类型(0：一级菜单 1：子菜单 2：按钮)
     * @param permission
     * @return
     */
    private JSONObject getMenuJsonObject(SysPermission permission) {
        JSONObject json = new JSONObject();
        if (permission.getMenuType() == 2) {
            return null;
        } else if (permission.getMenuType() == 0 || permission.getMenuType() == 1) {
            json.put("id", permission.getSysPermissionId());
            if (permission.getIsRoute()) {
                json.put("route", "1");// 表示生成路由
            } else {
                json.put("route", "0");// 表示不生成路由
            }
            if (isWWWHttpUrl(permission.getUrl())) {
                json.put("path", EncoderUtil.MD5Encode(permission.getUrl(), "utf-8"));
            } else {
                json.put("path", permission.getUrl());
            }
            // 重要规则：路由name (通过URL生成路由name,路由name供前端开发，页面跳转使用)
            if (BasisUtil.isNotEmpty(permission.getComponentName())) {
                json.put("name", permission.getComponentName());
            } else {
                json.put("name", urlToRouteName(permission.getUrl()));
            }
            // 是否隐藏路由，默认都是显示的
            if (permission.getHidden().equals(1)) {
                json.put("hidden", true);
            }
            // 聚合路由
            if (permission.getAlwaysShow() != null && permission.getAlwaysShow()) {
                json.put("alwaysShow", true);
            }
            json.put("component", permission.getComponent());
            JSONObject meta = new JSONObject();
            // 默认所有的菜单都加路由缓存，提高系统性能
            meta.put("keepAlive", "true");
            meta.put("title", permission.getName());
            if (BasisUtil.isEmpty(permission.getParentId())) {
                // 一级菜单跳转地址
                json.put("redirect", permission.getRedirect());
                if (BasisUtil.isNotEmpty(permission.getIcon())) {
                    meta.put("icon", permission.getIcon());
                }
            } else {
                if (BasisUtil.isNotEmpty(permission.getIcon())) {
                    meta.put("icon", permission.getIcon());
                }
            }
            if (isWWWHttpUrl(permission.getUrl())) {
                meta.put("url", permission.getUrl());
            }
            json.put("meta", meta);
        }
        return json;
    }


    /**
     *  组装前端按钮权限JSON数组——当前人的权限按钮
     *
     * @param jsonArray
     * @param metaList
     */
    private void getAuthJsonArray(JSONArray jsonArray,List<SysPermission> metaList) {
        for (SysPermission permission : metaList) {
            if(permission.getMenuType()==null) {
                continue;
            }
            JSONObject json = null;
            if(permission.getMenuType()==2&&"1".equals(permission.getStatus())) {
                json = new JSONObject();
                json.put("action", permission.getPermsCode());
                json.put("type", permission.getPermsType());
                json.put("describe", permission.getName());
                jsonArray.add(json);
            }
        }
    }

    /**
     *  组装前端按钮权限JSON数组——所有按钮
     * @param jsonArray
     * @param allList
     */
    private void getAllAuthJsonArray(JSONArray jsonArray,List<SysPermission> allList) {
        JSONObject json = null;
        for (SysPermission permission : allList) {
            json = new JSONObject();
            json.put("action", permission.getPermsCode());
            json.put("status", permission.getStatus());
            json.put("type", permission.getPermsType());
            json.put("describe", permission.getName());
            jsonArray.add(json);
        }
    }

    /**
     *  @DESC：判断是否外网URL
     *  例如： http://localhost:8080/lionherding/swagger-ui.html#/
     *  支持特殊格式： {{ *  window._CONFIG['domianURL'] }}/druid/ {{ JS代码片段 }}，前台解析会自动执行JS代码片段
     *
     * @param url
     * @return
     */
    private boolean isWWWHttpUrl(String url) {
        if (url != null && (url.startsWith("http://") || url.startsWith("https://") || url.startsWith("{{"))) {
            return true;
        }
        return false;
    }

    /**
     * @DESC：通过URL生成路由name
     *  去掉URL前缀斜杠，替换内容中的斜杠‘/’为-）
     *  举例： URL = /isystem/role RouteName = isystem-role
     *
     * @param url
     * @return
     */
    private String urlToRouteName(String url) {
        if (BasisUtil.isNotEmpty(url)) {
            if (url.startsWith("/")) {
                url = url.substring(1);
            }
            url = url.replace("/", "-");
            url = url.replace(":", "@");
            return url;
        } else {
            return null;
        }
    }

    @Override
    public List<SysPermissionTree> permissionlist() {
        List<SysPermission> list = this.baseMapper.selectList(new LambdaQueryWrapper<SysPermission>()
                .eq(SysPermission::getDelFlag,CommonConstant.DEL_FLAG_0)
                .orderByAsc(SysPermission::getSort)
        );
        List<SysPermissionTree> treeList = new ArrayList<>();
        getTreeList(treeList, list, null);
        return treeList;
    }

    /**
     *  组装菜单树
     * @param treeList
     * @param metaList
     * @param temp
     */
    private void getTreeList(List<SysPermissionTree> treeList, List<SysPermission> metaList, SysPermissionTree temp) {
        for (SysPermission permission : metaList) {
            String tempPid = permission.getParentId();
            SysPermissionTree tree = new SysPermissionTree(permission);
            if (temp == null && BasisUtil.isEmpty(tempPid)) {
                treeList.add(tree);
                if (!tree.isLeaf()) {
                    getTreeList(treeList, metaList, tree);
                }
            } else if (temp != null && tempPid != null && tempPid.equals(temp.getId())) {
                temp.getChildren().add(tree);
                if (!tree.isLeaf()) {
                    getTreeList(treeList, metaList, tree);
                }
            }

        }
    }
}
