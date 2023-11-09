package com.runde.signinbackend.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * @BelongsProject sign-in-backend
 * @BelongsPackage com.runde.signinbackend.config
 * @Author 最紧要开心
 * @CreateTime 2023/11/1 20:07
 * @Description MyBatis Plus配置
 * @Version 1.0
 */
public class MyBatisPlusConfig {

    /**
     * 添加分页插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
