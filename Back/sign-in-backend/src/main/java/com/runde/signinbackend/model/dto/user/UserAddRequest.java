package com.runde.signinbackend.model.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @BelongsProject sign-in-backend
 * @BelongsPackage com.runde.signinbackend.model.dto.user
 * @Author 最紧要开心
 * @CreateTime 2023/11/8 16:34
 * @Description 用户添加请求
 * @Version 1.0
 */
@Getter
@Setter
public class UserAddRequest implements Serializable {

    /**
     * 微信 UnionId
     */
    private String unionId;

    /**
     * 微信 OpenId
     */
    private String openId;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户名
     */
    private String name;

    /**
     * 个性签名
     */
    private String personalSgn;

    /**
     * 性别（0 - 女（默认），1 - 男）
     */
    private Integer sex;

    /**
     * 角色（admin|user）
     */
    private String role;

    private static final long serialVersionUID = 1L;
}
