package com.zhangzc.cloud.common.data.tenant;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;


@Configuration
public class CloudTenantConfiguration {

	@Bean
	public RequestInterceptor cloudFeignTenantInterceptor() {
		return new CloudFeignTenantInterceptor();
	}

	@Bean
	public ClientHttpRequestInterceptor cloudTenantRequestInterceptor() {
		return new TenantRequestInterceptor();
	}

}
