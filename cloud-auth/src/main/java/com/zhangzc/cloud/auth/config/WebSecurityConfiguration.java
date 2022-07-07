package com.zhangzc.cloud.auth.config;

import com.zhangzc.cloud.common.security.component.CloudDaoAuthenticationProvider;
import com.zhangzc.cloud.common.security.grant.CustomAppAuthenticationProvider;
import com.zhangzc.cloud.common.security.handler.FormAuthenticationFailureHandler;
import com.zhangzc.cloud.common.security.handler.SsoLogoutSuccessHandler;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * 认证相关配置
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/2/14 12:33 下午
 */
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    @Override
    @SneakyThrows
    protected void configure(HttpSecurity http) {
        http
                .formLogin().loginPage("/token/login").loginProcessingUrl("/token/form").failureHandler(authenticationFailureHandler())
                .and()
                .logout().logoutSuccessHandler(logoutSuccessHandler()).deleteCookies("JSESSIONID").invalidateHttpSession(true)
                .and()
                .authorizeRequests().antMatchers("/token/**", "/actuator/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }

    /**
     * 自定义provider注入
     * @param auth AuthenticationManagerBuilder
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        // 处理默认的密码模式认证
        CloudDaoAuthenticationProvider daoAuthenticationProvider = new CloudDaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        auth.authenticationProvider(daoAuthenticationProvider);
        // 处理自定义的认证模式
        auth.authenticationProvider(new CustomAppAuthenticationProvider());
    }

    /**
     * 认证中心静态资源处理
     * @param web WebSecurity
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**");
    }

    @Bean
    @Override
    @SneakyThrows
    public AuthenticationManager authenticationManagerBean() {
        return super.authenticationManagerBean();
    }

    /**
     * 密码处理器
     * @return 动态密码处理器 {类型}密文
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * sso 表单登录失败处理
     * @return FormAuthenticationFailureHandler
     */
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new FormAuthenticationFailureHandler();
    }

    /**
     * SSO 退出逻辑处理
     * @return LogoutSuccessHandler
     */
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new SsoLogoutSuccessHandler();
    }

}
