package com.runde.signinbackend.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @BelongsProject sign-in-backend
 * @BelongsPackage com.runde.signinbackend.model.vo
 * @Author 最紧要开心
 * @CreateTime 2023/11/7 11:22
 * @Description 用户视图（脱敏）
 * @Version 1.0
 */
@Getter
@Setter
public class UserVO implements Serializable {

    /**
     * 账号
     */
    @ApiModelProperty("账号")
    private String account;

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
    @ApiModelProperty("性别（0 - 女，1 - 男）")
    private Integer sex;

    /**
     * 角色（admin|user）
     */
    @ApiModelProperty("用户身份（admin|user）")
    private String role;

    private static final long serialVersionUID = 1L;
}
