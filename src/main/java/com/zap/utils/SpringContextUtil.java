package com.zap.utils;

/**
 * @ClassName SpringContextUtil
 * @Author Evan
 * @Descrption
 * @create 2022/9/6 11:00
 */

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * 上下文工具类
 *
 * @Author Asurplus
 */
@Slf4j
@Service
@Lazy(false)
public class SpringContextUtil implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext applicationContext = null;

    /**
     * 取得存储在静态变量中的ApplicationContext.
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 实现ApplicationContextAware接口, 注入Context到静态变量中.
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * 发布事件
     *
     * @param event
     */
    public static void publishEvent(ApplicationEvent event) {
        getApplicationContext().publishEvent(event);
    }

    /**
     * 实现DisposableBean接口, 在Context关闭时清理静态变量.
     */
    @Override
    @SneakyThrows
    public void destroy() {
        SpringContextUtil.clearHolder();
    }

    /**
     * 清除SpringContextHolder中的ApplicationContext为Null.
     */
    public static void clearHolder() {
        if (log.isDebugEnabled()) {
            log.debug("清除SpringContextHolder中的ApplicationContext:" + applicationContext);
        }
        applicationContext = null;
    }
}
