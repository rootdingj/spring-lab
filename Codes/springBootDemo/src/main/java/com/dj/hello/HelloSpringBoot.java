package com.dj.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: steven
 * @Date: 2020/9/24
 * @Description:
 */
@EnableAutoConfiguration
@RestController
public class HelloSpringBoot {

    @RequestMapping("hello")
    public String hello(){

        return "Hello SpringBoot";
    }

    public static void main(String[] args) {
        // 启动类，定制化参数
        SpringApplication.run(HelloSpringBoot.class, args);
    }

}
