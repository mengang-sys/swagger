package com.example.demo.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @ApiOperation(value = "hello",notes = "helloworld测试方法")
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(){
        return "HelloWorld,Spring Boot";
    }
}
