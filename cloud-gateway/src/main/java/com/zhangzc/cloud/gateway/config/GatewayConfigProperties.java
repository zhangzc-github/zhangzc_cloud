package com.zhangzc.cloud.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.List;

@ConfigurationProperties("gateway")
@Data
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
