package com.zhangzc.cloud.common.gateway.support;

import com.zhangzc.cloud.common.core.constant.CacheConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 动态路由检查检查
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/7/8 3:48 下午
 */
@Slf4j
@RequiredArgsConstructor
public class DynamicRouteHealthIndicator extends AbstractHealthIndicator {

	private final RedisTemplate redisTemplate;

	@Override
	protected void doHealthCheck(Health.Builder builder) {
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		if (redisTemplate.hasKey(CacheConstants.ROUTE_KEY)) {
			builder.up();
		}
		else {
			log.warn("动态路由监控检查失败，当前无路由配置");
			builder.down();
		}
	}

}
