package com.lh.system.config.mybatis;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.plugins.SqlExplainInterceptor;
import javafx.scene.paint.Stop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述：MyBatis-plus 代码生成器
 *
 * <p>版权所有：</p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author: 牧狮&&紫色年华
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
     * 执行分析插件 --当前配置无效
     * @return
     */
    @Bean
    public SqlExplainInterceptor sqlExplainInterceptor(){
        return new SqlExplainInterceptor();
    }

    /**
     * 性能分析插件 [生产环境可以关闭]
     * @return
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor(){
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        //SQL 是否格式化，默认关闭
        performanceInterceptor.setFormat(true);
        //sql 执行性能分析，开发环境使用，线上不推荐
        performanceInterceptor.setMaxTime(1500);
        return performanceInterceptor;
    }

}
