package com.zhangzc.cloud.common.log.annotation;

import java.lang.annotation.*;

/**
 * TODO
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/3/7 12:14 下午
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String value();
}
