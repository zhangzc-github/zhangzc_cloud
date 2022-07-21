package com.zhangzc.cloud.common.security.component;

import cn.hutool.core.util.ReUtil;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.zhangzc.cloud.common.security.annotation.Inner;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * 资源服务器直接对外暴露的url
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/2/16 11:50 上午
 */
@ConfigurationProperties(prefix = "security.oauth2.ignore")
public class PermitAllUrlProperties implements InitializingBean, ApplicationContextAware {
    private static final Pattern PATTERN = Pattern.compile("\\{(.*?)\\}");
    private ApplicationContext applicationContext;
    @Getter
    @Setter
    private List<String> urls = new ArrayList<>();
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() {
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();

        map.keySet().forEach(info -> {
            HandlerMethod handlerMethod = map.get(info);

            //获取方法上的注解， 替换 path variable 为 *
            Inner method = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), Inner.class);
            Optional.ofNullable(method).ifPresent(inner -> info.getPatternsCondition().getPatterns()
                    .forEach(url -> urls.add(ReUtil.replaceAll(url, PATTERN, StringPool.ASTERISK))));
            //获取类上的注解， 替换 path variable 为 *
//            Inner controller = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), Inner.class);
//            Optional.ofNullable(controller).ifPresent(inner -> info.getPatternsCondition().getPatterns()
//                    .forEach(url -> urls.add(ReUtil.replaceAll(url, PATTERN, StringPool.ASTERISK))));
        });

    }
}
