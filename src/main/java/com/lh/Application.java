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

    // todo 检查点击重新登录后是否清除token
    // TODO: 2019/10/30 设置操作是否成功，返回形式
    // todo log （参考jeecg 和 lionherding）
    // todo redis (参考jeecg)
    // TODO 2019/10/28 系统管理加权限 admin 和 administrator
    // TODO 2019/10/28 7 增删改查基础接口
    // todo  Excel 导入, 打印功能
    // todo 图表分析
    // TODO: 2019/11/1 多角色、多部门、多账号情景
    // TODO: 2019/11/3 对权限进行拦截 基于菜单，角色的配置

    // =========== shiro ===========
    // TODO: 2019/10/13 何时触发授权接口(config中配置，进行鉴权)
    // TODO: 2019/10/13 完成认证后如何保存认证信息及获取当前信息(token session等)
    // TODO: 2019/11/3 记住我

    // ============= VUE ============
    // TODO: 2019/10/23 src/permission.js -> 17 -> 认证成功后手动切换的页面
    // TODO: 2019/10/25  return new Promise((resolve, reject) => { 用法
    // TODO: 2019/10/28 crud 公共组件，CURD 通用模式
    // TODO: 2019/10/28 用户管理 ->职务，头像
    // TODO: 2019/11/2  构建统一部门树vo
    // TODO: 2019/11/3 点击登录后验证码失效，并且一段时间后也会失效

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
