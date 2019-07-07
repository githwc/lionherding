package com.lh.system.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 功能描述：浏览器环境下扩展点配置，
 *          配置在这里的bean，业务系统都可以通过声明同类型或同名的bean来覆盖安全
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author: 牧狮&&紫色年华
 * @Datetime: 2019-07-07
 * @Version: 1.0.0
 */
@Configuration
public class BrowerSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        // http.httpBasic()
        http.formLogin()    //配置身份验证为表单登录的方式
                .loginPage("/templates/in.html") //配置默认登录页
                .and()
                .authorizeRequests() //下面配置都是关于授权的配置
                .antMatchers("/templates/in.html").permitAll()   //此请求不需要认证
                .anyRequest()   //任何请求
                .authenticated();//需要身份验证
    }
}
