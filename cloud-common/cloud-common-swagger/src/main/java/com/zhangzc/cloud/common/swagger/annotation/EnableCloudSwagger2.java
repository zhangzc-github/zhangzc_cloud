package com.zhangzc.cloud.common.swagger.annotation;

import com.zhangzc.cloud.common.swagger.config.SwaggerAutoConfiguration;
import com.zhangzc.cloud.common.swagger.support.SwaggerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableConfigurationProperties(SwaggerProperties.class)
@Import(SwaggerAutoConfiguration.class)
public @interface EnableCloudSwagger2 {

}
