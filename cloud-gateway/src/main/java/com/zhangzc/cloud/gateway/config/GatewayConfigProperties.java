package com.zhangzc.cloud.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.List;

/**
 * 网关配置
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/2/22 6:26 下午
 */
@ConfigurationProperties("gateway")
@Data
@RefreshScope
public class GatewayConfigProperties {
    /**
     * 网关不需要验证码的客户端 {@link com.zhangzc.cloud.gateway.filter.ValidateCodeGatewayFilter}
     */
    private List<String> ignoreClients;

    /**
     * 网关解密前端登录密码 密钥 {@link com.zhangzc.cloud.gateway.filter.PasswordDecoderFilter}
     */
    private String encodeKey;

}
