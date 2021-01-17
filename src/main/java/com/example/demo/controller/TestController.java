package com.example.demo.controller;

import com.example.demo.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping(value = "/getparameter",method = RequestMethod.GET)
    public User getparameter(User user){
        return user;
    }

    @RequestMapping(value = "/getuser1",method = RequestMethod.GET)
    public User user1(){
        return new User(1,"mengyang");
    }

    @RequestMapping(value = "/postuser",method = RequestMethod.POST)
    public User postUser(User user){
        System.out.println("name"+user.getName());
        System.out.println("id"+user.getId());
        return user;
    }
}
