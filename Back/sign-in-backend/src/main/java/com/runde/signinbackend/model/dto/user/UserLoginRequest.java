package com.runde.signinbackend.model.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @BelongsProject sign-in-backend
 * @BelongsPackage com.runde.signinbackend.model.dto.user
 * @Author 最紧要开心
 * @CreateTime 2023/11/8 16:03
 * @Description 用户登录请求
 * @Version 1.0
 */
@Getter
@Setter
public class UserLoginRequest implements Serializable {

    /**
     * 账号
     */
    @ApiModelProperty(value = "账号", required = true)
    private String account;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    private static final long serialVersionUID = 1L;
}
