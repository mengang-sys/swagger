package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class User implements Serializable {

    private String id;
    private String name;
    private int age;
}
