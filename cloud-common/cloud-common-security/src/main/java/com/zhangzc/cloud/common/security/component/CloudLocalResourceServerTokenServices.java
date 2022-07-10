package com.zhangzc.cloud.common.security.component;

import cn.hutool.extra.spring.SpringUtil;
import com.zhangzc.cloud.common.security.service.CloudUser;
import com.zhangzc.cloud.common.security.service.CloudUserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

@RequiredArgsConstructor
public class CloudLocalResourceServerTokenServices implements ResourceServerTokenServices {

    private final TokenStore tokenStore;

    @Override
    public OAuth2Authentication loadAuthentication(String accessToken) throws AuthenticationException, InvalidTokenException {
        OAuth2Authentication oAuth2Authentication = tokenStore.readAuthentication(accessToken);
        if (oAuth2Authentication == null) return null;

        OAuth2Request oAuth2Request = oAuth2Authentication.getOAuth2Request();

        if (!(oAuth2Authentication.getPrincipal() instanceof CloudUser)) return oAuth2Authentication;

        UserDetailsService userDetailsService = SpringUtil.getBean(CloudUserDetailsServiceImpl.class);

        CloudUser cloudUser = (CloudUser) oAuth2Authentication.getPrincipal();

        UserDetails userDetails;
        try {
            userDetails = userDetailsService.loadUserByUsername(cloudUser.getUsername());
        } catch (UsernameNotFoundException e) {
            throw new OAuth2Exception(String.format("%s username not found", cloudUser.getUsername()));
        }

        Authentication userAuthentication = new UsernamePasswordAuthenticationToken(userDetails, "N/A",
                userDetails.getAuthorities());
        OAuth2Authentication authentication = new OAuth2Authentication(oAuth2Request, userAuthentication);
        authentication.setAuthenticated(true);
        return authentication;
    }

    @Override
    public OAuth2AccessToken readAccessToken(String accessToken) {
        throw new UnsupportedOperationException("Not supported: read access token");
    }
}
