package com.lh.system.config.security;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述：让读取配置文件的类生效
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author: 牧狮&&紫色年华
 * @Datetime: 2019-07-08
 * @Version: 1.0.0
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
}
