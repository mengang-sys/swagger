package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jettison.json.JSONString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/user")
@Api(description = "用户管理")
public class UserController {

    @Autowired
    private UserService userService;

    //添加用户
    @ApiOperation(value = "insertUser",notes = "添加用户")
    @PostMapping("/")
    public void insertUser(User user){
        System.out.println("==============");
         userService.insertUser(user);
         log.info("添加用户成功：{}", JSON.toJSONString(user));
         log.error("添加用户成功：{}", JSON.toJSONString(user));
         log.warn("添加用户成功：{}", JSON.toJSONString(user));
         log.debug("添加用户成功：{}", JSON.toJSONString(user));
         log.trace("添加用户成功：{}", JSON.toJSONString(user));
    }

    //查找用户
    @ApiOperation(value = "findUserById",notes = "查找用户")
    @GetMapping("/{id}")
    public void findUserById(@PathVariable long id){
         User user = userService.findUserById(id);
        System.out.println(user.getId()+"=="+user.getName());

    }

    //修改用户
    @ApiOperation(value = "updateUserById",notes = "修改用户")
    @PutMapping("/{id}")
    public User updateUserById(User user){
        return userService.updateUserById(user);
    }

    //删除用户
    @ApiOperation(value = "deleteUserById",notes = "删除用户")
    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable long id){
        userService.deleteUserById(id);
    }
}
