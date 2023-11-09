package com.runde.signinbackend.model.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @BelongsProject sign-in-backend
 * @BelongsPackage com.runde.signinbackend.model.dto.user
 * @Author 最紧要开心
 * @CreateTime 2023/11/9 9:49
 * @Description 用户注册请求
 * @Version 1.0
 */
@Getter
@Setter
public class UserRegisterRequest implements Serializable {

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 二次输入密码
     */
    private String checkPassword;

    private static final long SerialVersionUID = 1L;
}
