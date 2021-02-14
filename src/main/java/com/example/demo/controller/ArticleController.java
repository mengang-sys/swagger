package com.example.demo.controller;

import com.example.demo.entity.Article;
import com.example.demo.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("article")
@Slf4j
public class ArticleController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ArticleService articleService;

    @GetMapping("/{id}")
    public Article testPathVariable(@PathVariable("id") Integer id) {
        Article articles = articleService.findArticleById(id);
        if(articles.getView()>0){
            //val+1
            stringRedisTemplate.boundValueOps("name::"+id).increment(articles.getView()+1);
            log.info("点击量大于0 ："+stringRedisTemplate.opsForValue().get("name::"+id));
        }else{//val+1
            stringRedisTemplate.boundValueOps("name::"+id).increment(1);
            log.info("点击量不大于0 ："+stringRedisTemplate.opsForValue().get("name::"+id));
        }
        return articles;
    }
}
