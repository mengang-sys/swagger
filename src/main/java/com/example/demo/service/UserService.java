package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "users")
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Cacheable(key = "#p0")
    public User selectUser(String id){
        System.out.println("select");
        return userMapper.findById(id);
    }

    @CachePut(key = "#p0.id")
    public void updateById(User user){
        System.out.println("update");
        userMapper.updateById(user);
    }
    //如果allEntries指定为true，则调用CacheEvict方法后将立即清空所有缓存
    @CacheEvict(key = "#p0",allEntries = true)
    public void deleteById(String id){
        System.out.println("delete");
        userMapper.deleteById(id);
    }
}
