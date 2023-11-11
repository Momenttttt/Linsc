package com.runde.signinbackend.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @BelongsProject sign-in-backend
 * @BelongsPackage com.runde.signinbackend.utils
 * @Author 最紧要开心
 * @CreateTime 2023/11/1 21:26
 * @Description SQL工具类
 * @Version 1.0
 */
public class SqlUtil {

    /**
     * 检测排序字段是否合法（防止SQL注入）
     *
     * @param sortField
     */
    public static Boolean validSortField(String sortField) {
        if (StringUtils.isBlank(sortField)) {
            return false;
        }
        return !StringUtils.containsAny(sortField, "=", "(", ")", " ");
    }
}
