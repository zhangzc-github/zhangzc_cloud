package com.zhangzc.cloud.auth.config;

import com.zhangzc.cloud.common.core.constant.SecurityConstants;
import com.zhangzc.cloud.common.security.component.CloudWebResponseExceptionTranslator;
import com.zhangzc.cloud.common.security.service.CloudClientDetailsServiceImpl;
import com.zhangzc.cloud.common.security.service.CloudUser;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


/**
 * 认证服务器配置
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/2/14 12:31 下午
 */
@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    private final DataSource dataSource;
    private final AuthenticationManager authenticationManager;
    private final TokenStore tokenStore;

    @Override
    @SneakyThrows
    public void configure(ClientDetailsServiceConfigurer clients) {
        CloudClientDetailsServiceImpl clientDetailsService = new CloudClientDetailsServiceImpl(dataSource);
        clientDetailsService.setSelectClientDetailsSql(SecurityConstants.DEFAULT_SELECT_STATEMENT);
        clientDetailsService.setFindClientDetailsSql(SecurityConstants.DEFAULT_FIND_STATEMENT);
        clients.withClientDetails(clientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.allowFormAuthenticationForClients().checkTokenAccess("permitAll()");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager)
                .tokenStore(tokenStore)
                .tokenEnhancer(tokenEnhancer())
                .exceptionTranslator(new CloudWebResponseExceptionTranslator());
    }

    /**
     * token 增强
     * @return tokenEnhancer
     */
    @Bean
    public TokenEnhancer tokenEnhancer(){
        return (accessToken, authentication) -> {
            Map<String, Object> additionalInfo = new HashMap<>(4);
            additionalInfo.put(SecurityConstants.DETAILS_LICENSE, SecurityConstants.PROJECT_LICENSE);
            String clientId = authentication.getOAuth2Request().getClientId();
            additionalInfo.put(SecurityConstants.CLIENT_ID, clientId);

            CloudUser pigUser = (CloudUser) authentication.getUserAuthentication().getPrincipal();
            additionalInfo.put(SecurityConstants.DETAILS_USER, pigUser);
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            return accessToken;
        };
    }

}
