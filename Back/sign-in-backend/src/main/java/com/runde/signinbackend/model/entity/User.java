package com.runde.signinbackend.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户
 * @TableName sg_user
 */
@TableName(value ="sg_user")
@Data
public class User implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 微信 UnionId
     */
    @ApiModelProperty("微信 unionId")
    private String unionId;

    /**
     * 微信 OpenId
     */
    @ApiModelProperty("微信 openId")
    private String openId;

    /**
     * 账号
     */
    @ApiModelProperty("账号")
    private String account;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
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
    @ApiModelProperty("性别（0 - 女，1 - 男）")
    private Integer sex;

    /**
     * 角色（admin|user）
     */
    @ApiModelProperty("用户身份（admin|user）")
    private String role;

    /**
     * 状态（0 - 封禁，1 - 正常（默认））
     */
    @ApiModelProperty("用户状态（0 - 封禁，1 - 正常）")
    private Integer status;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty("最近更新时间")
    private LocalDateTime updateTime;

    /**
     * 是否删除（0 - 未删除，1 - 删除）
     */
    @TableLogic
    @ApiModelProperty("是否删除（0 - 未删除，1 - 删除）")
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}