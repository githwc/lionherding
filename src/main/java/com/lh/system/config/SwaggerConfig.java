package com.lh.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 功能描述：swagger相关配置
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author: 牧狮&&紫色年华
 * @Datetime: 2019-07-07
 * @Version: 1.0.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {
    @Bean
    public Docket createRestApi() {
        // //=====添加head参数start============================
        // ParameterBuilder tokenPar = new ParameterBuilder();
        // List<Parameter> pars = new ArrayList<Parameter>();
        // tokenPar.name("Authorization").description("AccessToken令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        // pars.add(tokenPar.build());
        // // =========添加head参数end===================
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("后台管理服务API")
                .description("后台管理服务API文档说明")
                // .contact(new Contact("lionherding", "www.baidu.com", "1197798263@qq.com"))
                .version("2.0")
                .build();
    }


}

