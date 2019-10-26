package com.yihaoxiong.cms.service.impl;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.yihaoxiong.cms.domain.Article;
import com.yihaoxiong.common.utils.DateUtil;
import com.yihaoxiong.common.utils.RandomUtils;
import com.yihaoxiong.common.utils.StreamUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class ParseArticle2RedisAndKafka {

  @Resource
  RedisTemplate redisTemplate;

  @Resource
  KafkaTemplate<String, String> kafkaTemplate;

  @Test
  public void readArticle() {
    //声明文章保存路径
    File file = new File("D:/爬虫");
    File[] listFiles = file.listFiles();
    //遍历文章所在的目录
    for (int i = 0; i < listFiles.length - 1; i++) {
      if (listFiles[i].isFile()) {

        //判断是否是文件
        String name = listFiles[i].getName();
        //获取文章标题
        String title = name.replace(".txt", "");
        //获取文章内容
        String content = StreamUtil.readTextFile(listFiles[i]);
        //System.out.println(context);
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        Calendar instance = Calendar.getInstance();
        instance.set(2019, 1, 1, 0, 0, 0);

        article.setCreated(DateUtil.randomDate(instance.getTime(), new Date()));
        article.setHits(RandomUtils.random(0, 10000));
        article.setHot(RandomUtils.random(0, 1));
        //截取140个字为摘要
        String summery = content.substring(0, 140);
        article.setSummary(summery);
        article.setCategoryId(1);
        article.setChannelId(2);
        String jsonString = JSON.toJSONString(article);
        //把每一篇文章保存到Redis
        redisTemplate.opsForValue().set("redis_article_" + i, jsonString);

        //从Redis中获取文章，并且发送到kafka
        String redisDB = (String) redisTemplate.opsForValue().get("redis_article_" + i);
        //发送到kafka
        kafkaTemplate.send("article", redisDB);
      }
    }
  }
}
