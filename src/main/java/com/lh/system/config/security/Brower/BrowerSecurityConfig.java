package com.lh.system.config.security.Brower;

import com.lh.system.config.security.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
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

    // @Autowired
    // private SecurityProperties securityProperties;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        /**
         *  取消Security登录验证
         */
        // http.authorizeRequests()
        //         .anyRequest().permitAll()
        //         .and().logout().permitAll();

        /**
         *  忽略druid的csrf校验
         */
        http.csrf().ignoringAntMatchers("/druid/*");

        /**
         * 基础登录验证样式
         */
        // http.httpBasic()

        /**
         * 配置身份验证为表单登录的方式
         */
        http.formLogin()
                .loginPage("/login")           //配置默认登录页(security判断是否已经登陆授权，否则跳到这里) (配合配置的视图控制器使用)
                .loginProcessingUrl("/user/login")  //自定义的登录接口
                .and()
                .authorizeRequests()                //定义不需要认证的请求
                .antMatchers("/login","/user/login").permitAll()
                .anyRequest()                       //定义需要认证的请求
                .authenticated()
                .and()
                .csrf().disable()       //关闭csrf防护
        ;
    }

}
