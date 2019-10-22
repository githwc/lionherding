package com.lh.common.config.mybatis;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.plugins.SqlExplainInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 功能描述：MyBatis-plus 配置类
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author:  xieyc
 * @Datetime: 2019-06-28 17:17
 * @Version: 1.0.0
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 分页插件
     *
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 执行分析插件(目前只支持 MYSQL-5.6.3 以上版本)
     *      分析处理 DELETE UPDATE 语句，防止恶意delete update 全表操作
     *      stopProceed 设置true 为开启拦截
     *  【建议开发环境使用，生产环境关闭即可】
     * @return
     */
    @Bean
    public SqlExplainInterceptor sqlExplainInterceptor(){
        SqlExplainInterceptor sqlExplainInterceptor = new SqlExplainInterceptor();
        Properties prop = new Properties();
        prop.setProperty("stopProceed","true");
        sqlExplainInterceptor.setProperties(prop);
        return sqlExplainInterceptor;
    }

    /**
     * 性能分析插件 【建议开发环境使用，生产环境关闭即可】
     * @return
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor(){
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        //SQL 是否格式化，默认关闭
        performanceInterceptor.setFormat(false);
        /** sql 执行性能分析，开发环境使用，线上不推荐 MaxTime(1500) 指的是最大执行时长 */
        // performanceInterceptor.setMaxTime(1500);
        return performanceInterceptor;
    }

}
