package com.lh.system.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 功能描述：使用WebMvcConfigurerAdapter扩展SpringMvc功能
 * <p>
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author: 牧狮&&紫色年华
 * @Datetime: 2019-05-30 11:12
 */

// @EnableWebMvc //全面接管webmvc：启动spring mvc特性，也就是说可以通过这个注解，然后在java代码代码中实现对js或css的页面的过滤/拦截
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {

    // @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            //登录拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/index.html","/","/user/login");
            }
            //视图控制器
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("index/login");
                registry.addViewController("/index.html").setViewName("index/login");
                //登录成功后重定向到首页
                registry.addViewController("/main.html").setViewName("dashboard");
            }
        };
        return adapter;
    }
}
