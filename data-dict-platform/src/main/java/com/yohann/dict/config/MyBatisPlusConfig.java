package com.yohann.dict.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * mybatis plus 配置
 * </p>
 *
 * @author Yohann
 */
@Configuration
@MapperScan("com.yohann.*.mapper")
public class MyBatisPlusConfig {
    /**
     * 分页插件
     *
     * @return 返回分页器
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}