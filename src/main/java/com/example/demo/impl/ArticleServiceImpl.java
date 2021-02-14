package com.example.demo.impl;

import com.example.demo.entity.Article;
import com.example.demo.service.ArticleRepository;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@CacheConfig(cacheNames = "articleService")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public List<Article> getArticleList() {
        return articleRepository.findAll();
    }

    @Override
    @Cacheable(key = "#p0")
    public Article findArticleById(long id) {
        return articleRepository.findById(id);
    }
}
