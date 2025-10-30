package com.dj.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Auther: steven
 * @Date: 2020/10/5
 * @Description: Eureka 服务注册中心
 */
@SpringBootApplication
@EnableEurekaServer
public class RegisterEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegisterEurekaServerApplication.class, args);
    }

}
