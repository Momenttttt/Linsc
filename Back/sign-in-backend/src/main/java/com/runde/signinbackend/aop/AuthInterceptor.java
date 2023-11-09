package com.runde.signinbackend.aop;

import com.runde.signinbackend.annotation.AuthCheck;
import com.runde.signinbackend.common.ErrorCode;
import com.runde.signinbackend.constant.UserConstant;
import com.runde.signinbackend.exception.BusinessException;
import com.runde.signinbackend.model.entity.User;
import com.runde.signinbackend.model.enums.UserRoleEnum;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @BelongsProject sign-in-backend
 * @BelongsPackage com.runde.signinbackend.aop
 * @Author 最紧要开心
 * @CreateTime 2023/11/1 23:38
 * @Description 权限校验AOP
 * @Version 1.0
 */
@Aspect
@Component
public class AuthInterceptor {

    @Around("@within(authCheck)")
    public Object doInterceptorAllClass(ProceedingJoinPoint point, AuthCheck authCheck) throws Throwable {
        return doInterceptorCurrentMethod(point, authCheck);
    }

    @Around("@annotation(authCheck)")
    public Object doInterceptorCurrentMethod(ProceedingJoinPoint point, AuthCheck authCheck) throws Throwable {
        // 必须具备的身份
        String[] roles = authCheck.mustRole();
        // 获取当前请求
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

        // 获取登录用户
        User user = (User) request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        if (user == null || user.getId() == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }

        if (roles.length != 0) {
            for (String role : roles) {
                if (UserRoleEnum.getEnumByValue(role) == null) {
                    throw new BusinessException(ErrorCode.SYSTEM_ERROR);

                }
            }
            // 判断登录用户状态
            if (user.getStatus() == 0) {
                throw new BusinessException(ErrorCode.FORBIDENT_ERROR, "用户已被封");
            }
            // 判断登录用户权限
            String role = user.getRole();
            if(!StringUtils.equalsAny(role, roles)) {
                throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
            }
        }

        return point.proceed();
    }
}
