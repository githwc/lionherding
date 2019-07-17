package com.lh.system.config.filterAndInterceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 功能描述：拦截器
 *      拦截器可以记录请求的类和方法，但是无法获取访问的参数
 *      可以通过Spring AOP编程 切片方法解决
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author: 牧狮&&紫色年华
 * @Datetime: 2019-05-28
 */
// @Component
public class MyInterceptorConfig implements HandlerInterceptor{

    //目标方法执行之前
    // @Override
    // public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
    //     System.out.println("======preHandle()==============");
    //     httpServletRequest.setAttribute("startTime",new Date().getTime());
    //     System.out.println("类名："+((HandlerMethod)o).getBean().getClass().getName());
    //     System.out.println("方法名："+((HandlerMethod)o).getMethod().getName());
    //     //登录检查
    //     Object user = httpServletRequest.getSession().getAttribute("loginUser");
    //     System.out.println("===LionHerding===值=" + new Date() + "," + "当前类=LoginHandlerInterceptor.preHandle()");
    //     if(user == null){//未登录，返回到登录页面
    //         httpServletRequest.setAttribute("msg","没有权限请先登录！");
    //         //转发
    //         httpServletRequest.getRequestDispatcher("/index.html").forward(httpServletRequest,httpServletResponse);
    //     }else{//已登陆
    //         return true;
    //     }
    //     return true;
    // }
    //
    // @Override
    // public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    //     System.out.println("======postHandle()==============");
    //     System.out.println("耗时："+ (new Date().getTime()-(Long)httpServletRequest.getAttribute("startTime")));
    // }
    //
    // @Override
    // public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    //     System.out.println("==========afterCompletion()===========");
    //     System.out.println("耗时："+ (new Date().getTime()-(Long)httpServletRequest.getAttribute("startTime")));
    // }
}
