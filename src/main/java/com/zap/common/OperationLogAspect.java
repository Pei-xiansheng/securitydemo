package com.zap.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;

/**
 * @author: Apeng
 * @version: 1.0
 * @date: 2022/5/28 17:03
 */

@Aspect
@Component
public class OperationLogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(OperationLogAspect.class);

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * 设置操作日志切入点 在注解的位置切入代码
     */
    @Pointcut("@annotation(com.zap.common.OperationLog)")
    public void logPointCut() {
    }

    @Before(value = "logPointCut()")
    public void doBefore() {
        startTime.set(System.currentTimeMillis());
    }


    /**
     * 记录操作日志(处理完请求后执行)
     *
     * @param joinPoint 方法的执行点（切点）
     * @param result    方法的返回值
     * @throws Throwable
     */
    @AfterReturning(returning = "result", value = "logPointCut()")
    public void doAfterReturning(JoinPoint joinPoint, Object result) throws Throwable {
        //保存处理信息
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        OperationLog annotation = method.getAnnotation(OperationLog.class);

    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 方法的执行点（切点）
     * @param e         异常
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        //保存异常信息
    }
}
