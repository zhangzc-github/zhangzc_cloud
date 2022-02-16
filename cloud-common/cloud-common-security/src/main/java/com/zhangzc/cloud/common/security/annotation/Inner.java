package com.zhangzc.cloud.common.security.annotation;

import java.lang.annotation.*;

/**
 * 服务调用不用鉴权注解
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/2/16 10:36 上午
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Inner {
    /**
     * 是否AOP统一处理
     * @return boolean
     */
    boolean value() default true;
}
