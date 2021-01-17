package com.example.demo.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestControllerTest {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Test
    public void nparameters(){
        RestTemplate client = restTemplateBuilder.build();
        ResponseEntity<String> responseEntity = client.getForEntity("http://localhost:8080/getuser1", String.class);
        System.out.println("=============="+responseEntity.getBody());
    }
}