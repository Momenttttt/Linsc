package com.runde.signinbackend.model.enums;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * @BelongsProject sign-in-backend
 * @BelongsPackage com.runde.signinbackend.model
 * @Author 最紧要开心
 * @CreateTime 2023/11/7 12:13
 * @Description 用户身份枚举
 * @Version 1.0
 */
@Getter
public enum UserRoleEnum {

    USER("用户", "user"),
    ADMIN("管理员", "admin"),
    TEACHER("教师", "teacher"),
    ASSISTANT("助教", "assistant");

    private final String text;

    private final String value;

    UserRoleEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value
     */
    public static UserRoleEnum getEnumByValue(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        for (UserRoleEnum userRoleEnum : UserRoleEnum.values()) {
            if (StringUtils.equals(userRoleEnum.value, value)) {
                return userRoleEnum;
            }
        }
        return null;
    }
}
