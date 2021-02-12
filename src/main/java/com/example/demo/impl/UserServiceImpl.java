package com.example.demo.impl;

import com.example.demo.entity.User;
import com.example.demo.service.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;

@CacheConfig(cacheNames = "user")
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    //查找用户
    @Override
    @Cacheable(key = "#id")
    public User findUserById(long id){
        User user = userRepository.findUserById(id);
        return user;
    }

    //新增用户
    @Override
    @CachePut(key = "#user.id")
    public User insertUser(User user) {
        user = this.userRepository.save(user);
        return user;
    }

    //修改用户
    @Override
    @CachePut(key = "#user.id")
    public User updateUserById(User user) {
        return userRepository.save(user);
    }

    //删除用户
    @Override
    @CacheEvict(key = "#id")
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

/*    private static NumberFormat numberFormat = NumberFormat.getPercentInstance();

    static {
        numberFormat.setMaximumFractionDigits(3);
    }

    public static void main(String[] args) {
        System.out.println(numberFormat.format(0.1223123));
        BigDecimal b1 = new BigDecimal("1.123");
        BigDecimal b2 = new BigDecimal("2.234");
        final String format = numberFormat.format(b1.divide(b2, 4, BigDecimal.ROUND_HALF_UP));
        System.out.println(format+"==============");
    }*/
}
