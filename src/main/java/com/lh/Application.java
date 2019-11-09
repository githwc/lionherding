package com.lh;

import com.lh.common.utils.LocalHostUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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

    // TODO 2019/10/28 系统管理加权限拦截  admin 和 administrator -- 基于菜单，角色的配置
    // TODO: 2019/10/13 何时触发授权接口(config中配置，进行鉴权)
    // TODO: 2019/10/13 完成认证后如何保存认证信息及获取当前信息(token session等)
    // TODO: 2019/11/3 记住我

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
