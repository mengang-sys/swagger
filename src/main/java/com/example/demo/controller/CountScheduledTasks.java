package com.example.demo.controller;

import com.example.demo.entity.Article;
import com.example.demo.service.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
@Slf4j
@Component
@Transactional
public class CountScheduledTasks {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Scheduled(cron = "0 */2 * * * ?") //每隔两分钟执行一次
    public void syncPostViews(){
        log.info("开始执行定时任务redis和数据库数据同步");
        Set<String> keySet = stringRedisTemplate.keys("name::*");
        for (String key : keySet) {
            String views = stringRedisTemplate.opsForValue().get(key);
            String sid = key.replaceAll("name::", "");
            Long lid = Long.parseLong(sid);
            long lviews = Long.parseLong(views);
            //批量更新可以用Collection<?>实现
            articleRepository.updateArticleViewById(lviews,lid);
            stringRedisTemplate.delete(key);
        }
    }
}
