package com.zhangzc.cloud.gateway.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangzc.cloud.gateway.filter.CloudRequestGlobalFilter;
import com.zhangzc.cloud.gateway.filter.PasswordDecoderFilter;
import com.zhangzc.cloud.gateway.filter.ValidateCodeGatewayFilter;
import com.zhangzc.cloud.gateway.handler.GlobalExceptionHandler;
import com.zhangzc.cloud.gateway.handler.ImageCodeCheckHandler;
import com.zhangzc.cloud.gateway.handler.ImageCodeCreateHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Gateway 配置类
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/2/22 12:41 下午
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(GatewayConfigProperties.class)
public class GatewayConfiguration {

    @Bean
    public ValidateCodeGatewayFilter validateCodeGatewayFilter(ObjectMapper objectMapper, RedisTemplate redisTemplate) {
        return new ValidateCodeGatewayFilter(objectMapper, redisTemplate);
    }

    @Bean
    public PasswordDecoderFilter passwordDecoderFilter(GatewayConfigProperties configProperties, RedisTemplate redisTemplate) {
        return new PasswordDecoderFilter(configProperties, redisTemplate);
    }

    @Bean
    public CloudRequestGlobalFilter cloudRequestGlobalFilter() {
        return new CloudRequestGlobalFilter();
    }

    @Bean
    public GlobalExceptionHandler globalExceptionHandler(ObjectMapper objectMapper) {
        return new GlobalExceptionHandler(objectMapper);
    }

    @Bean
    public ImageCodeCreateHandler imageCodeCreateHandler(ObjectMapper objectMapper) {
        return new ImageCodeCreateHandler(objectMapper);
    }
    @Bean
    public ImageCodeCheckHandler imageCodeCheckHandler(ObjectMapper objectMapper) {
        return new ImageCodeCheckHandler(objectMapper);
    }

}
