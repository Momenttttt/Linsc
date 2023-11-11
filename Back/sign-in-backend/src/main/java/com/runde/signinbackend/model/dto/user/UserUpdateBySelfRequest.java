package com.runde.signinbackend.model.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @BelongsProject sign-in-backend
 * @BelongsPackage com.runde.signinbackend.model.dto.user
 * @Author 最紧要开心
 * @CreateTime 2023/11/9 0:04
 * @Description 用户修改个人信息请求
 * @Version 1.0
 */
@Getter
@Setter
public class UserUpdateBySelfRequest implements Serializable {

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
     * 性别（0 - 女，1 - 男）
     */
    @ApiModelProperty("性别（0 - 女，1 - 男）")
    private Integer sex;

    private static final long serialVersionUID = 1L;
}
