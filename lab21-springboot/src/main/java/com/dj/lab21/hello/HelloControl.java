package com.dj.lab21.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloControl {

    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        System.out.println("Hello World");
        return "Hello World";
    }

}
