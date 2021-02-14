package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class RedisController {

    @Autowired
    private UserService userService;

    @RequestMapping("/{id}")
    public User ForTest(@PathVariable String id){
        return userService.selectUser(id);
    }

    @RequestMapping("/update/")
    public String update(User user){
        userService.updateById(user);
        return "success";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        userService.deleteById(id);
        return "delete success";
    }
}
