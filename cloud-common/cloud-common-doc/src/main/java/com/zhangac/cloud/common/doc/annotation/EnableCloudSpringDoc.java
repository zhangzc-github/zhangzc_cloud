package com.zhangac.cloud.common.doc.annotation;

import com.zhangac.cloud.common.doc.config.SpringDocAutoConfiguration;
import com.zhangac.cloud.common.doc.support.SpringDocProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableConfigurationProperties(SpringDocProperties.class)
@Import({SpringDocAutoConfiguration.class})
public @interface EnableCloudSpringDoc {
}
