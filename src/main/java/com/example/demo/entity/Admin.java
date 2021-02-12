package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity //管理员实体
@Data
public class Admin implements Serializable {

    @Id
    @GeneratedValue
    @Column(unique = true)
    private Integer id;

    private String username;

    private String name;

    private String password;

    private String salt;//加密密码的盐
    //用户状态 0，创建未认证 比如没有激活， 没有输入验证码等，等待验证的用户  1 正常状态 2用户被锁定
    private byte state;

    //立即从数据库中加载数据
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SysUserRole",joinColumns = {@JoinColumn(name="uid")},inverseJoinColumns = {@JoinColumn(name="roleId")})
    private List<SysRole> roleList;
}
