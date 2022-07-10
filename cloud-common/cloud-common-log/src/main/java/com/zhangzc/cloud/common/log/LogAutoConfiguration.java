package com.zhangzc.cloud.common.log;

import com.zhangzc.cloud.common.log.aspect.SysLogAspect;
import com.zhangzc.cloud.common.log.event.SysLogListener;
import com.zhangzc.cloud.upms.api.feign.RemoteLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 日志自动配置
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/3/7 12:13 下午
 */
@EnableAsync
@ConditionalOnWebApplication
@Configuration(proxyBeanMethods = false)
@RequiredArgsConstructor
public class LogAutoConfiguration {

    private final RemoteLogService remoteLogService;

    @Bean
    public SysLogListener sysLogListener() {
        return new SysLogListener(remoteLogService);
    }

    @Bean
    public SysLogAspect sysLogAspect() {
        return new SysLogAspect();
    }
}
