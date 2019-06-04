package com.lh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
 * @Company: LionHerding
 * @Author: 牧狮&&紫色年华
 * @Datetime: 2019-05-30
 */
@MapperScan({"com.lh.system.mapper","com.lh.modules.mapper"})
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
