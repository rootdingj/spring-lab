package com.dj.properties;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther: steven
 * @Date: 2020/9/24
 * @Description: 自定义 PropertySource
 */
@SpringBootApplication
@Slf4j
public class PropertySourceDemoApplication implements ApplicationRunner {

    @Value("${geektime.greeting}")
    private String greeting;

    public static void main(String[] args) {
        SpringApplication.run(PropertySourceDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("{}", greeting);
    }
}
