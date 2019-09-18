package com.lh.system.config.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 功能描述：读取配置文件中所有以lh.security开头的项
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author: 牧狮&&紫色年华
 * @Datetime: 2019-07-08
 * @Version: 1.0.0
 */
// @ConfigurationProperties(prefix = "lh.security")
// @Data
public class SecurityProperties {

    private BrowerProperties brower = new BrowerProperties();
}
