package com.zhangzc.cloud.admin;

import com.zhangzc.cloud.common.swagger.annotation.EnableCloudSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
@EnableFeignClients(basePackages = "com.zhangzc.cloud")
@EnableDiscoveryClient
@EnableCloudSwagger2
public class CloudAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudAdminApplication.class, args);
    }

}
