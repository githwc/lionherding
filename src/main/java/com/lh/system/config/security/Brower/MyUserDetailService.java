package com.lh.system.config.security.Brower;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 功能描述:用户认证逻辑
 *      根据自己的用户体系配置用户认证逻辑
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author: 牧狮&&紫色年华
 * @Datetime: 2019-07-07
 * @Version: 1.0.0
 */
// @Component
public class MyUserDetailService /*implements UserDetailsService*/ {

    private Logger logger = LoggerFactory.getLogger(getClass());

    // @Autowired
    // private PasswordEncoder passwordEncoder;
    //
    // @Override
    // public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //     logger.info("登录用户名"+ username);
    //     //根据用户名查找用户信息与权限
    //
    //     //封装用户信息,并返回。参数分别是:用户名,密码,用户权限
    //     return new User(username,passwordEncoder.encode("123456"),
    //             true,true,true,true,
    //             AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    //
    // }
}
