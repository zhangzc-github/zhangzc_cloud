package com.zhangzc.cloud.common.gateway.rule;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.zhangzc.cloud.common.core.constant.CommonConstants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.http.server.reactive.ServerHttpRequest;
import java.util.List;
import java.util.Map;

@Slf4j
@AllArgsConstructor
public class VersionGrayLoadBalancer implements GrayLoadBalancer {
    private final DiscoveryClient discoveryClient;
    @Override
    public ServiceInstance choose(String serviceId, ServerHttpRequest request) {
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
        //注册中心无该服务实例，抛出异常
        if (CollUtil.isEmpty(instances)) {
            log.warn("No instance available for {}", serviceId);
            throw new NotFoundException("No instance available for " + serviceId);
        }
        //获取请求头中的version，无则随机返回实例
        String requestVersion = request.getHeaders().getFirst(CommonConstants.VERSION);
        if (StrUtil.isBlank(requestVersion)) return instances.get(RandomUtil.randomInt(instances.size()));
        //遍历实例元数据，若匹配则返回实例 TODO: 应该有问题，返回始终会是同一个？
        for (ServiceInstance instance : instances) {
            Map<String, String> metadata = instance.getMetadata();
            String targetVersion = MapUtil.getStr(metadata, CommonConstants.VERSION);
            if (requestVersion.equalsIgnoreCase(targetVersion)) {
                log.debug("gray request match successfully:{} {}", requestVersion, instance);
                return instance;
            }
        }

        return instances.get(RandomUtil.randomInt(instances.size()));
    }
}
