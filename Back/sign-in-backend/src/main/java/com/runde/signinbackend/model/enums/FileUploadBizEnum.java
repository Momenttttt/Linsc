package com.runde.signinbackend.model.enums;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * @BelongsProject sign-in-backend
 * @BelongsPackage com.runde.signinbackend.model.enums
 * @Author 最紧要开心
 * @CreateTime 2023/11/9 0:24
 * @Description 文件上传业务类型枚举
 * @Version 1.0
 */
@Getter
public enum FileUploadBizEnum {

    USER_AVATAR("用户头像", "user_avatar");

    private final String text;

    private final String value;

    FileUploadBizEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public static FileUploadBizEnum getEnumByValue(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        for (FileUploadBizEnum fileUploadBizEnum : FileUploadBizEnum.values()) {
            if (StringUtils.equals(fileUploadBizEnum.value, value)) {
                return fileUploadBizEnum;
            }
        }
        return null;
    }
}
