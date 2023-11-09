package com.runde.signinbackend.common;

import lombok.Getter;

/**
 * @BelongsProject sign-in-backend
 * @BelongsPackage com.runde.signinbackend.common
 * @Author 最紧要开心
 * @CreateTime 2023/11/1 20:12
 * @Description 自定义错误码
 * @Version 1.0
 */
@Getter
public enum ErrorCode {

    PARAMS_ERROR("Params Error", "参数错误"),
    OPERATION_FAILED_ERROR("Operation Failed", "操作失败"),
    NOT_LOGIN_ERROR("Not Login", "未登录"),
    FORBIDENT_ERROR("Forbident", "禁止访问"),
    NO_AUTH_ERROR("Insufficient Auth", "权限不足"),
    NOT_FOUND_ERROR("Data Not Found", "数据未找到"),
    SYSTEM_ERROR("System Error", "系统内部异常");

    private final String code;

    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
