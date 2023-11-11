package com.runde.signinbackend.model.dto.user;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "微信 unionId", required = true)
    private String unionId;

    /**
     * 微信 OpenId
     */
    @ApiModelProperty(value = "微信 openId", required = true)
    private String openId;

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

    /**
     * 用户头像
     */
    @ApiModelProperty("用户头像")
    private String avatar;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String name;

    /**
     * 个性签名
     */
    @ApiModelProperty("个性签名")
    private String personalSgn;

    /**
     * 性别（0 - 女（默认），1 - 男）
     */
    @ApiModelProperty("性别（0 - 女（默认），1 - 男）")
    private Integer sex;

    /**
     * 角色（admin|user（默认））
     */
    @ApiModelProperty("用户身份（admin|user（默认））")
    private String role;

    private static final long serialVersionUID = 1L;
}
