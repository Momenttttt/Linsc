package com.runde.signinbackend.utils;


import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject sign-in-backend
 * @BelongsPackage com.runde.signinbackend.utils
 * @Author 最紧要开心
 * @CreateTime 2023/11/1 21:29
 * @Description Spring 上下文获取工具
 * @Version 1.0
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * 通过名称获取bean
     *
     * @param beanName
     */
    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    /**
     * 通过类型获取bean
     *
     * @param beanClass
     */
    public static <T> T getBean(Class<T> beanClass) {
        return applicationContext.getBean(beanClass);
    }

    /**
     * 通过名称和类型获取bean
     *
     * @param beanName
     * @param beanClass
     */
    public static <T> T getBean(String beanName, Class<T> beanClass) {
        return applicationContext.getBean(beanName, beanClass);
    }
}
