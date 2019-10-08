package com.lh.common.config.shiro;

import com.lh.common.config.filter.JwtFilter;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
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
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/sysUser/login", "anon"); //登录接口
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/swagger**/**", "anon");

        filterChainDefinitionMap.put("/test","anon");   //Temp APi
        filterChainDefinitionMap.put("/login","anon");  //Temp APi
        filterChainDefinitionMap.put("/login2","anon");  //Temp APi

        //权限访问
        filterChainDefinitionMap.put("/add","perms[hr:BaseSettings]");

        //认证后可访问
        filterChainDefinitionMap.put("/*","authc");
        filterChainDefinitionMap.put("/add","authc");   // Temp API
        filterChainDefinitionMap.put("/update","authc");    //Temp API

        //设置默认登录页面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        //设置默认未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");

        // 添加自己的过滤器并且取名为jwt
        // Map<String, Filter> filterMap = new HashMap<String, Filter>(1);
        // filterMap.put("jwt", new JwtFilter());
        // shiroFilterFactoryBean.setFilters(filterMap);
        // <!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边
        // filterChainDefinitionMap.put("/**", "jwt");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager(ShiroRealm myRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm);

        /*
         * 关闭shiro自带的session，详情见文档
         * http://shiro.apache.org/session-management.html#SessionManagement-
         * StatelessApplications%28Sessionless%29
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        return securityManager;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
