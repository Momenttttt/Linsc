package com.runde.signinbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @BelongsProject sign-in-backend
 * @BelongsPackage com.zrd.signinbackend.config
 * @Author 最紧要开心
 * @CreateTime 2023/11/1 19:57
 * @Description Knife4j配置
 * @Version 1.0
 */
@Configuration
@EnableSwagger2
@Profile({"dev", "test"})
public class Knife4jConfig {

    @Bean
    public Docket defaultApi3() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(new ApiInfoBuilder()
                        .title("sign-in-backend 接口文档")
                        .description("sign-in 后端应用")
                        .version("0.0.1")
                        .contact(new Contact("最紧要开心","",""))
                        .build())
                .groupName("backend")
                .select()
                // 指定 Controller 扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.runde.signinbackend.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
