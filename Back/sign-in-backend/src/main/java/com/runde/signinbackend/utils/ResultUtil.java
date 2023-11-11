package com.runde.signinbackend.utils;

import com.runde.signinbackend.common.BaseResponse;
import com.runde.signinbackend.common.ErrorCode;

/**
 * @BelongsProject sign-in-backend
 * @BelongsPackage com.runde.signinbackend.utils
 * @Author 最紧要开心
 * @CreateTime 2023/11/1 21:13
 * @Description 返回结果工具类
 * @Version 1.0
 */
public class ResultUtil {

    /**
     * 成功
     *
     * @param data  响应数据
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>("Success", data, "成功");
    }

    /**
     * 错误
     *
     * @param errorCode 自定义错误码
     */
    public static BaseResponse error(ErrorCode errorCode) {
        return new BaseResponse(errorCode);
    }

    /**
     * 错误
     *
     * @param errorCode 自定义错误码
     * @param message 消息
     */
    public static BaseResponse error(ErrorCode errorCode, String message) {
        return new BaseResponse(errorCode.getCode(), null, message);
    }

    /**
     * 错误
     *
     * @param code 错误码
     * @param message 消息
     */
    public static BaseResponse error(String code, String message) {
        return new BaseResponse(code, null, message);
    }

    /**
     * 错误
     *
     * @param code 错误码
     */
    public static BaseResponse error(String code) {
        return new BaseResponse(code);
    }
}
