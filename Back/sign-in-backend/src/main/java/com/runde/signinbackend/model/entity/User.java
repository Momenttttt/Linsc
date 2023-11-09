package com.runde.signinbackend.model.entity;

import com.baomidou.mybatisplus.annotation.*;
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
    private Long id;

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

    /**
     * 状态（0 - 封禁，1 - 正常（默认））
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否删除（0 - 未删除，1 - 删除）
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}