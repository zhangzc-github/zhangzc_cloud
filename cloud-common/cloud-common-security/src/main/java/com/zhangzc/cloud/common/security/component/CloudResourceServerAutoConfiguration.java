package com.zhangzc.cloud.common.security.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

public class CloudResourceServerAutoConfiguration {
    @Bean("pms")
    public PermissionService permissionService() {
        return new PermissionService();
    }

    @Bean
    @Primary
    public ResourceServerTokenServices resourceServerTokenServices(TokenStore tokenStore, UserDetailsService userDetailsService) {
        return new CloudLocalResourceServerTokenServices(tokenStore, userDetailsService);
    }
}
