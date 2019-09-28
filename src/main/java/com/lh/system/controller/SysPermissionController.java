package com.lh.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lh.common.config.exception.parameterException.ParameterException;
import com.lh.common.config.response.HttpResponseUtil;
import com.lh.common.config.response.ResponseBean;
import com.lh.common.utils.BasisUtil;
import com.lh.common.utils.EncoderUtil;
import com.lh.common.utils.JwtUtil;
import com.lh.system.entity.SysPermission;
import com.lh.system.service.SysPermissionService;
import com.lh.system.utils.PermissionDataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author   xieyc
 * @Date 2019-09-20
 * @Version: 1.0.0
 *
 */
@RestController
@RequestMapping("/sysPermission")
@Slf4j
public class SysPermissionController {

    @Autowired
    public SysPermissionService iSysPermissionService;

  /**
    * 查询用户拥有的菜单权限和按钮权限（根据TOKEN）
    *
    * @return
    */
    @GetMapping(value = "/getUserPermissionByToken")
    public JSONObject getUserPermissionByToken(@RequestParam(name = "token", required = true) String token, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        try {
            if (BasisUtil.isEmpty(token)) {
                throw new ParameterException("参数错误:TOKEN不允许为空");
            }
            log.info(" ------ 通过令牌获取用户拥有的访问菜单 ---- TOKEN ------ " + token);
            String username = JwtUtil.getUsername(token);
            List<SysPermission> metaList = iSysPermissionService.queryPermissionByUser(username);
            PermissionDataUtil.addIndexPage(metaList);
            JSONArray menujsonArray = new JSONArray();
            this.getPermissionJsonArray(menujsonArray, metaList, null);
            JSONArray authjsonArray = new JSONArray();
            this.getAuthJsonArray(authjsonArray, metaList);
            // 查询所有的权限
            // LambdaQueryWrapper<SysPermission> query = new LambdaQueryWrapper<SysPermission>();
            // query.eq(SysPermission::getDelFlag, 0);
            // query.eq(SysPermission::getMenuType, 2);
            // //query.eq(SysPermission::getStatus, "1");
            // List<SysPermission> allAuthList = iSysPermissionService.list(query);
            List<SysPermission> allAuthList = iSysPermissionService.queryPermissionByArgs(0,2);
            JSONArray allauthjsonArray = new JSONArray();
            this.getAllAuthJsonArray(allauthjsonArray, allAuthList);
            json.put("menu", menujsonArray);
            json.put("auth", authjsonArray);
            json.put("allAuth", allauthjsonArray);
        } catch (Exception e) {
            HttpResponseUtil.sendJson(response,ResponseBean.error(500,"系统错误，请联系管理员！"+e.getMessage()));
            log.error(e.getMessage(), e);
        }
        return json;
    }


    /**
     *  获取菜单JSON数组
     * @param jsonArray
     * @param metaList
     * @param parentJson
     */
    private void getPermissionJsonArray(JSONArray jsonArray, List<SysPermission> metaList, JSONObject parentJson) {
        for (SysPermission permission : metaList) {
            if (permission.getMenuType() == null) {
                continue;
            }
            String tempPid = permission.getParentId();
            JSONObject json = getPermissionJsonObject(permission);
            if(json==null) {
                continue;
            }
            if (parentJson == null && BasisUtil.isEmpty(tempPid)) {
                jsonArray.add(json);
                if (!permission.getIsLeaf()) {
                    getPermissionJsonArray(jsonArray, metaList, json);
                }
            } else if (parentJson != null && BasisUtil.isNotEmpty(tempPid) && tempPid.equals(parentJson.getString("id"))) {
                // 类型( 0：一级菜单 1：子菜单 2：按钮 )
                if (permission.getMenuType() == 2) {
                    JSONObject metaJson = parentJson.getJSONObject("meta");
                    if (metaJson.containsKey("permissionList")) {
                        metaJson.getJSONArray("permissionList").add(json);
                    } else {
                        JSONArray permissionList = new JSONArray();
                        permissionList.add(json);
                        metaJson.put("permissionList", permissionList);
                    }
                    // 类型( 0：一级菜单 1：子菜单 2：按钮 )
                } else if (permission.getMenuType() == 1 || permission.getMenuType() == 0) {
                    if (parentJson.containsKey("children")) {
                        parentJson.getJSONArray("children").add(json);
                    } else {
                        JSONArray children = new JSONArray();
                        children.add(json);
                        parentJson.put("children", children);
                    }

                    if (!permission.getIsLeaf()) {
                        getPermissionJsonArray(jsonArray, metaList, json);
                    }
                }
            }

        }
    }

    /**
     *  获取权限JSON数组
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
                json.put("action", permission.getPerms());
                json.put("type", permission.getPermsType());
                json.put("describe", permission.getName());
                jsonArray.add(json);
            }
        }
    }


    private JSONObject getPermissionJsonObject(SysPermission permission) {
        JSONObject json = new JSONObject();
        // 类型(0：一级菜单 1：子菜单 2：按钮)
        if (permission.getMenuType() == 2) {
            //json.put("action", permission.getPerms());
            //json.put("type", permission.getPermsType());
            //json.put("describe", permission.getName());
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
            if (permission.getAlwaysShow()) {
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
     * 判断是否外网URL 例如： http://localhost:8080/jeecg-boot/swagger-ui.html#/ 支持特殊格式： {{
     * window._CONFIG['domianURL'] }}/druid/ {{ JS代码片段 }}，前台解析会自动执行JS代码片段
     *
     * @return
     */
    private boolean isWWWHttpUrl(String url) {
        if (url != null && (url.startsWith("http://") || url.startsWith("https://") || url.startsWith("{{"))) {
            return true;
        }
        return false;
    }

    /**
     * 通过URL生成路由name（去掉URL前缀斜杠，替换内容中的斜杠‘/’为-） 举例： URL = /isystem/role RouteName =
     * isystem-role
     *
     * @return
     */
    private String urlToRouteName(String url) {
        if (BasisUtil.isNotEmpty(url)) {
            if (url.startsWith("/")) {
                url = url.substring(1);
            }
            url = url.replace("/", "-");

            // 特殊标记
            url = url.replace(":", "@");
            return url;
        } else {
            return null;
        }
    }

    /**
     *  获取权限JSON数组
     * @param jsonArray
     * @param allList
     */
    private void getAllAuthJsonArray(JSONArray jsonArray,List<SysPermission> allList) {
        JSONObject json = null;
        for (SysPermission permission : allList) {
            json = new JSONObject();
            json.put("action", permission.getPerms());
            json.put("status", permission.getStatus());
            json.put("type", permission.getPermsType());
            json.put("describe", permission.getName());
            jsonArray.add(json);
        }
    }


}
