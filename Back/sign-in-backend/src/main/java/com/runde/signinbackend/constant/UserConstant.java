package com.runde.signinbackend.constant;

/**
 * @BelongsProject sign-in-backend
 * @BelongsPackage com.runde.signinbackend.constant
 * @Author 最紧要开心
 * @CreateTime 2023/11/7 13:12
 * @Description 用户常量
 * @Version 1.0
 */
public interface UserConstant {

    String USER_LOGIN_STATE = "user_login";

    // region 权限

    /**
     * 管理员
     */
    String ADMIN_ROLE = "admin";

    /**
     * 教师
     */
    String TEACHER_ROLE = "teacher";

    /**
     * 助教
     */
    String ASSISTANT_ROLE = "assistant";

    /**
     * 用户
     */
    String USER_ROLE = "user";

    // endregion
}
