package com.zhangzc.cloud.common.gateway.config;

import com.zhangzc.cloud.common.gateway.filter.GrayReactiveLoadBalancerClientFilter;
import com.zhangzc.cloud.common.gateway.rule.GrayLoadBalancer;
import com.zhangzc.cloud.common.gateway.rule.VersionGrayLoadBalancer;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.config.GatewayLoadBalancerProperties;
import org.springframework.cloud.gateway.config.GatewayReactiveLoadBalancerClientAutoConfiguration;
import org.springframework.cloud.gateway.filter.ReactiveLoadBalancerClientFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = "gray.rule.enabled", havingValue = "true")
@AutoConfigureBefore(GatewayReactiveLoadBalancerClientAutoConfiguration.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
public class GrayLoadBalancerClientConfiguration {

    @Bean
    public ReactiveLoadBalancerClientFilter gatewayLoadBalancerClientFilter(GrayLoadBalancer grayLoadBalancer,
                                                                            GatewayLoadBalancerProperties loadBalancerProperties) {
        return new GrayReactiveLoadBalancerClientFilter(loadBalancerProperties, grayLoadBalancer);
    }

    @Bean
    public GrayLoadBalancer grayLoadBalancer(DiscoveryClient discoveryClient) {
        return new VersionGrayLoadBalancer(discoveryClient);
    }

}
