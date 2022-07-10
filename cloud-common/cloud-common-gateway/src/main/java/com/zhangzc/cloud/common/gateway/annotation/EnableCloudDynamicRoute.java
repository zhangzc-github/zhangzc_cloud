package com.zhangzc.cloud.common.gateway.annotation;

import com.zhangzc.cloud.common.gateway.config.DynamicRouteAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启 动态路由
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/7/8 3:45 下午
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(DynamicRouteAutoConfiguration.class)
public @interface EnableCloudDynamicRoute {

}
