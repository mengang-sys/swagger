package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {

    User findUserById(long id);

    User insertUser(User user);

    User updateUserById(User user);

    void deleteUserById(long id);
}
