package com.runde.signinbackend.exception;

import com.runde.signinbackend.common.ErrorCode;
import lombok.Getter;

/**
 * @BelongsProject sign-in-backend
 * @BelongsPackage com.runde.signinbackend.exception
 * @Author 最紧要开心
 * @CreateTime 2023/11/1 22:58
 * @Description 自定义业务异常
 * @Version 1.0
 */
@Getter
public class BusinessException extends RuntimeException {

    /**
     * 错误码
     */
    private final String code;

    public BusinessException(ErrorCode errorCode) {
        this(errorCode.getCode(), errorCode.getMessage());
    }

    public BusinessException(ErrorCode errorCode, String message) {
        this(errorCode.getCode(), message);
    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }
}
