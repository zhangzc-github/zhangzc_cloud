package com.zhangzc.cloud.common.security.service;

import com.zhangzc.cloud.common.core.constant.CacheConstants;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import javax.sql.DataSource;

public class CloudClientDetailsServiceImpl extends JdbcClientDetailsService {
    public CloudClientDetailsServiceImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    @Cacheable(value = CacheConstants.CLIENT_DETAILS_KEY, key = "#clientId", unless = "#result == null")
    public ClientDetails loadClientByClientId(String clientId) {
        return super.loadClientByClientId(clientId);
    }
}
