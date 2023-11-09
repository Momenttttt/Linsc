package com.runde.signinbackend.model.dto.user;

import com.runde.signinbackend.common.PageRequest;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @BelongsProject sign-in-backend
 * @BelongsPackage com.runde.signinbackend.model.dto.user
 * @Author 最紧要开心
 * @CreateTime 2023/11/8 22:45
 * @Description 用户查询多个请求
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class UserQueryListRequest extends PageRequest implements Serializable {

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
     * 是否删除（0 - 未删除，1 - 删除）
     */
    private Integer isDelete;

    private static final long serialVersionUID = 1L;
}
