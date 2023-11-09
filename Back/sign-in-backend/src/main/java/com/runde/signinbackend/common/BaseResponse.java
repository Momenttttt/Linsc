package com.runde.signinbackend.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @BelongsProject sign-in-backend
 * @BelongsPackage com.runde.signinbackend.common
 * @Author 最紧要开心
 * @CreateTime 2023/11/1 20:30
 * @Description 通用响应类
 * @Version 1.0
 */
@Getter
@Setter
public class BaseResponse<T> implements Serializable {

    /**
     * 状态码
     */
    private String code;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 消息
     */
    private String message;

    private static final long serialVersionUID = 1L;

    public BaseResponse(String code) {
        this(code, null, "");
    }

    public BaseResponse(String code, T data) {
        this(code, data, "");
    }

    public BaseResponse(String code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage());
    }

}
