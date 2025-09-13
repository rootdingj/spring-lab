package com.dj.lab21.config;

import com.dj.lab21.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration：声明配置类相当于Spring的配置文件
 *
 * 在配置文件中用<bean><bean/>标签添加组件
 *
 */
@Configuration
public class MyAppConfig {

    //将方法的返回值添加到容器中；容器中这个组件默认的id就是方法名
    @Bean
    public HelloService helloService(){
        System.out.println("MyAppConfig 通过 @Bean 给容器中添加ID为helloService的组件");
        return new HelloService();
    }

}
