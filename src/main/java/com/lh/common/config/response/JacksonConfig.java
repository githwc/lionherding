package com.lh.common.config.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * 功能描述：统一配置返回JSON
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2019-10-12
 * @Version: 1.0.0
 */
@Configuration
public class JacksonConfig {

    /**
     * 对Mapper对象进行设置，所有序列化的对象都将按规则进行序列化
     *
     *  注：不参加序列化，返回的json是没有这个字段的。这样对移动端会更省流量
     *      ALWAYS: 默认
     *      NON_DEFAULT: 属性为默认值不序列化
     *      NON_EMPTY: 属性为空("")或NULL都不序列化
     *      NON_NULL: 属性为NULL，不序列化
     * @param builder
     * @return
     */
    @Bean
    // 当出现多个Bean候选者时，被注解为@Primary的Bean将作为首选者，否则将抛出异常
    @Primary
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper jackObjectMapper(Jackson2ObjectMapperBuilder builder){
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper;
    }

}
