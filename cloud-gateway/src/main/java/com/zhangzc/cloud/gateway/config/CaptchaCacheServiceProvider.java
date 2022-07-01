package com.zhangzc.cloud.gateway.config;

import com.anji.captcha.service.CaptchaCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import java.util.concurrent.TimeUnit;

/**
 * 验证码 缓存提供支持集群,需要通过SPI
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/7/1 11:24 上午
 */
public class CaptchaCacheServiceProvider implements CaptchaCacheService {

    private static final String TYPE = "REDIS";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void set(String key, String value, long expiresInSeconds) {
        stringRedisTemplate.opsForValue().set(key, value, expiresInSeconds, TimeUnit.SECONDS);
    }

    @Override
    public boolean exists(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    @Override
    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public String type() {
        return TYPE;
    }
}
