package com.zhangzc.cloud.admin.config;

import com.zhangzc.cloud.common.security.component.CloudResourceServerAutoConfiguration;
import com.zhangzc.cloud.common.security.component.PermitAllUrlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

/**
 * 认证相关配置
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/2/14 12:33 下午
 */
@Configuration
@EnableConfigurationProperties(PermitAllUrlProperties.class)
@Import({CloudResourceServerAutoConfiguration.class})
public class CloudResourceServerConfigurerAdapter extends ResourceServerConfigurerAdapter {

    @Autowired
    private ResourceServerTokenServices resourceServerTokenServices;
    @Autowired
    private PermitAllUrlProperties permitAllUrlProperties;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();
        permitAllUrlProperties.getUrls().stream().forEach(url -> registry.antMatchers(url).permitAll());
        registry.anyRequest().authenticated()
                .and()
                .csrf().disable();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.tokenServices(resourceServerTokenServices);
    }
}
