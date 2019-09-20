package com.lh.system.config.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 功能描述：shiro配置类
 *
 *      1.创建ShiroFilterFactoryBean
 *      2.创建DefaultWebSecurityManager
 *      3.创建Realm
 *
 * <p>版权所有：</p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2019-09-17 16:25
 * @Version: 1.0.0
 */
@Configuration
public class ShiroConfig {

    /**
     * 定义Filter Chain,实现权限相关的拦截
     *  注: LinkedHashMap 顺序拦截
     *
     *      anon: 无需认证即可访问
     *      authc:必须认证才可以访问
     *      user: 需要认证或通过记住我认证才能访问
     *      perms:必须得到资源权限才可以访问
     *      roles:必须得到角色权限才可以访问
     *
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 放行
        filterChainDefinitionMap.put("/test","anon");
        filterChainDefinitionMap.put("/login","anon");

        //权限访问
        filterChainDefinitionMap.put("/add","perms[hr:BaseSettings]");

        //认证后可访问
        // filterChainDefinitionMap.put("/add","authc");
        // filterChainDefinitionMap.put("/update","authc");
        filterChainDefinitionMap.put("/*","authc");

        //设置默认登录页面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        //设置默认未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager(ShiroRealm myRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm);

        return securityManager;
    }

}
