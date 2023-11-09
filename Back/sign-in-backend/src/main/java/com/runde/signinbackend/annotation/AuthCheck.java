package com.runde.signinbackend.annotation;

import java.lang.annotation.*;

/**
 * @BelongsProject sign-in-backend
 * @BelongsPackage com.runde.signinbackend.annotation
 * @Author 最紧要开心
 * @CreateTime 2023/11/1 23:17
 * @Description 权限校验注解
 * @Version 1.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface AuthCheck {

    /**
     * 必须具备的身份
     */
    String[] mustRole();
}
