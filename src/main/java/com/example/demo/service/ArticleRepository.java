package com.example.demo.service;

import com.example.demo.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ArticleRepository extends JpaRepository<Article, Long>, JpaSpecificationExecutor<Article> {

    Article findById(long id);

    @Modifying
    @Query("update Article a set a.view = ?1 where a.id =?2")
    void updateArticleViewById(long lviews, Long lid);
}
