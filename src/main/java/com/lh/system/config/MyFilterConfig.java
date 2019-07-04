package com.lh.system.config;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * 功能描述：过滤器
 *      如果在此类上配置@Component注解，会导致过滤所有请求
 *
 *      过滤器存在问题：只能拦截请求，并且拿到请求和相应信息，但是并不知道这个请求是哪个方法处理
 *                    因为Filter是J2EE技术，而Controller是SpringMVC中的技术
 *                    应该使用拦截器 Interceptor
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author: 牧狮&&紫色年华
 * @Datetime: 2019-07-04
 * @Version: 1.0.0
 */
// @Component
public class MyFilterConfig implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("当前类======Filter.init() ==== 方法开始执行===== ");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long startTime = new Date().getTime();
        chain.doFilter(request,response);
        System.out.println("方法耗时："+ (new Date().getTime()-startTime));
    }

    @Override
    public void destroy() {
        System.out.println("当前类======Filter.init() ==== 方法销毁===== ");
    }
}
