package com.lh.system.config.filterAndInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 功能描述：
 *      配置类：用来注册配置扩展springMvc功能
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author: 牧狮&&紫色年华
 * @Datetime: 2019-05-27
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    // @Autowired
    // private MyInterceptorConfig myInterceptorConfig;

    /**
    * @Description:登录拦截器
    * @Date: 23:51 2019/7/4
    * @Param:
    * @Return:
    * @throws:
    */
    // @Override
    // public void addInterceptors(InterceptorRegistry registry){
    //     registry.addInterceptor(myInterceptorConfig).addPathPatterns("/**")
    //             .excludePathPatterns("/index.html","/","/user/login");
    // }


    /**
    * @Description:配置过滤器
    * @Date: 23:10 2019/7/4
    * @Param:
    * @Return:
    * @throws:
    */
    // @Bean
    // public FilterRegistrationBean filterRegistrationBean(){
    //     FilterRegistrationBean registration = new FilterRegistrationBean();
    //     MyFilterConfig myFilterConfig = new MyFilterConfig();
    //     registration.setFilter(myFilterConfig);
    //     //表明拦截哪些请求
    //     List<String> list = new ArrayList<>();
    //     list.add("/*");
    //     registration.setUrlPatterns(list);
    //
    //     return registration;
    // }

    /**
    * @Description:视图控制器
    * @Date: 23:52 2019/7/4
    * @Param:
    * @Return:
    * @throws:
    */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // registry.addViewController("/").setViewName("login");
        registry.addViewController("/login").setViewName("login");
        //登录成功后重定向到首页
        // registry.addViewController("/main.html").setViewName("dashboard");
    }

}
