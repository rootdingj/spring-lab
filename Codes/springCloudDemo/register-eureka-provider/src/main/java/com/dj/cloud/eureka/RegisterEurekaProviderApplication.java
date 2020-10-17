package com.dj.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Auther: steven
 * @Date: 2020/10/5
 * @Description: EurekaClient 服务提供者
 */
@SpringBootApplication
//@EnableEurekaClient
@EnableDiscoveryClient
public class RegisterEurekaProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegisterEurekaProviderApplication.class, args);
    }

}
