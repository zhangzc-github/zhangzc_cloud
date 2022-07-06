package com.zhangzc.cloud.common.data.mybatis;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.zhangzc.cloud.common.data.tenant.CloudTenantConfigProperties;
import com.zhangzc.cloud.common.data.tenant.CloudTenantHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CloudTenantConfigProperties.class)
public class MybatisPlusConfiguration {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 多租户支持
        TenantLineInnerInterceptor tenantLineInnerInterceptor = new TenantLineInnerInterceptor();
        tenantLineInnerInterceptor.setTenantLineHandler(cloudTenantHandler());
        interceptor.addInnerInterceptor(tenantLineInnerInterceptor);
        return interceptor;
    }

    @Bean
    @ConditionalOnMissingBean
    public CloudTenantHandler cloudTenantHandler() {
        return new CloudTenantHandler();
    }

    @Bean
    public CloudSqlInjector cloudSqlInjector() {
        return new CloudSqlInjector();
    }
}
