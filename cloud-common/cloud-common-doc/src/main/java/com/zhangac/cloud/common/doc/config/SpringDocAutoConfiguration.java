package com.zhangac.cloud.common.doc.config;

import com.zhangac.cloud.common.doc.support.SpringDocProperties;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.Scopes;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@ConditionalOnProperty(name = "spring-doc.enabled", matchIfMissing = true)
public class SpringDocAutoConfiguration {

	private final SpringDocProperties springDocProperties;

	private final ServiceInstance serviceInstance;

	@Bean
	public OpenAPI springOpenAPI() {
		OpenAPI openAPI = new OpenAPI().info(new Info().title(springDocProperties.getTitle()));
		// oauth2.0 password
		openAPI.schemaRequirement(HttpHeaders.AUTHORIZATION, this.securityScheme());
		// servers
		List<Server> serverList = new ArrayList<>();
		String path = springDocProperties.getServices().get(serviceInstance.getServiceId());
		serverList.add(new Server().url(springDocProperties.getGateway() + "/" + path));
		openAPI.servers(serverList);
		return openAPI;
	}

	private SecurityScheme securityScheme() {
		OAuthFlow clientCredential = new OAuthFlow();
		clientCredential.setTokenUrl(springDocProperties.getTokenUrl());
		clientCredential.setScopes(new Scopes().addString(springDocProperties.getScope(), springDocProperties.getScope()));
		OAuthFlows oauthFlows = new OAuthFlows();
		oauthFlows.password(clientCredential);
		SecurityScheme securityScheme = new SecurityScheme();
		securityScheme.setType(SecurityScheme.Type.OAUTH2);
		securityScheme.setFlows(oauthFlows);
		return securityScheme;
	}

}
