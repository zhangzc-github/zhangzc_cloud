package com.zhangzc.cloud.common.gateway.filter;

import com.zhangzc.cloud.common.gateway.rule.GrayLoadBalancer;
import org.springframework.cloud.client.loadbalancer.LoadBalancerProperties;
import org.springframework.cloud.gateway.config.GatewayLoadBalancerProperties;
import org.springframework.cloud.gateway.filter.ReactiveLoadBalancerClientFilter;

public class GrayReactiveLoadBalancerClientFilter extends ReactiveLoadBalancerClientFilter {
    private static final int LOAD_BALANCER_CLIENT_FILTER_ORDER = 10150;

    private GatewayLoadBalancerProperties loadBalancerProperties;

    private LoadBalancerProperties properties;

    private GrayLoadBalancer grayLoadBalancer;

    public GrayReactiveLoadBalancerClientFilter(GatewayLoadBalancerProperties loadBalancerProperties,
                                                LoadBalancerProperties properties, GrayLoadBalancer grayLoadBalancer) {
        super(null, loadBalancerProperties, properties);
        this.properties = properties;
        this.grayLoadBalancer = grayLoadBalancer;
        this.loadBalancerProperties = loadBalancerProperties;
    }

}
