package com.zhangzc.cloud.common.security.component;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * RedisTokenAutoConfiguration
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/2/28 11:48 上午
 */
public class TokenStoreAutoConfiguration {
    @Bean
    public TokenStore tokenStore(RedisConnectionFactory redisConnectionFactory) {
        return new RedisTokenStore(redisConnectionFactory);
    }
}
