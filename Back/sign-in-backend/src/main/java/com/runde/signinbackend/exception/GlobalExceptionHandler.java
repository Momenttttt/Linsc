package com.runde.signinbackend.exception;

import com.runde.signinbackend.common.BaseResponse;
import com.runde.signinbackend.common.ErrorCode;
import com.runde.signinbackend.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @BelongsProject sign-in-backend
 * @BelongsPackage com.runde.signinbackend.exception
 * @Author 最紧要开心
 * @CreateTime 2023/11/1 23:07
 * @Description 全局异常处理器
 * @Version 1.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获自定义业务异常
     *
     * @param e 自定义业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e) {
        log.error("A BusinessException has occurred, code: {}, message: {}", e.getCode(), e.getMessage());
        return ResultUtil.error(e.getCode(), e.getMessage());
    }

    /**
     * 捕获运行时异常
     *
     * @param e 运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("A RuntimeException has occurred, message: {}", e.getMessage());
        return ResultUtil.error(ErrorCode.SYSTEM_ERROR);
    }
}
