package com.lh;

import com.lh.common.utils.LocalHostUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 功能描述：
 * 主程序类、主入口类
 *      @SpringBootApplication：标注一个主程序类，说明这是一个Spring Boot 应用
 *          @Target({ElementType.TYPE})
 *          @Retention(RetentionPolicy.RUNTIME)
 *          @Documented
 *          @Inherited
 *          @SpringBootConfiguration:Spring Boot的配置类
 *          @EnableAutoConfiguration：
 *              开启自动配置功能
 *              将主配置类（@SpringBootApplication标注的类）的所在包及下面所有子包里面的所有组件扫描到Spring容器；
 *          @ComponentScan
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author:  xieyc
 * @Datetime: 2019-05-30
 */
@SpringBootApplication
@MapperScan({"com.lh.system.mapper", "com.lh.modules.*.mapper"})
@Slf4j
public class Application {

    // todo 1 检查点击重新登录后是否清除token
    // TODO: 2019/10/13 整理一份初始化的表
    // todo 3 日志中记录当前操作人
    // todo 4 log （参考jeecg 和 lionherding）
    // todo 5 redis (参考jeecg)
    // todo 12 Excel 导入, 打印功能
    // todo 13 图表分析

    // =========== shiro ===========
    // TODO: 2019/10/13 何时触发认证接口(subject.login()后调用)，何时触发授权接口(config中配置，进行鉴权)
    // TODO: 2019/10/13 权限资源如何传递给前端
    // TODO: 2019/10/13 完成认证后如何保存认证信息及获取当前信息(token session等)


    // ============= VUE ============
    // TODO: 2019/10/23 permission -> 17 -> 认证成功后手动切换页面
    // TODO: 2019/10/25  return new Promise((resolve, reject) => { 用法
    public static void main(String[] args) {

        ConfigurableApplicationContext application = SpringApplication.run(Application.class, args);
        Environment env = application.getEnvironment();
        String ip = LocalHostUtil.getIpAddress();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");
        log.info("\n----------------------------------------------------------\n\t" +
                "Application LionHerding is running! Access URLs:\n\t" +
                "Local: \t\thttp://localhost:" + port + path + "/\n\t" +
                "External: \thttp://" + ip + ":" + port + path + "/\n\t" +
                "swagger-ui: http://" + ip + ":" + port + path + "/swagger-ui.html\n\t" +
                "----------------------------------------------------------");

	}
}
