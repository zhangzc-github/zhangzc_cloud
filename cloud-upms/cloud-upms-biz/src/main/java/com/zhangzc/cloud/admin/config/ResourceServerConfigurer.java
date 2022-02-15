package com.zhangzc.cloud.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 认证相关配置
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/2/14 12:33 下午
 */
@Configuration
public class ResourceServerConfigurer extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests().antMatchers("/user/info/**").permitAll().anyRequest().authenticated()
                .and()
                .csrf().disable();
    }
}
