package com.zap.common;

import java.lang.annotation.*;

/**
 * @author: Apeng
 * @version: 1.0
 * @date: 2022/5/28 16:59
 */


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLog {

    //操作模块
    String model() default "";
    //操作类型
    String type() default "";
    //操作描述
    String desc() default "";
}
