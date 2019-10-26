package com.yihaoxiong.cms.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.listener.MessageListener;

import com.alibaba.fastjson.JSON;
import com.yihaoxiong.cms.domain.Article;
import com.yihaoxiong.cms.service.ArticleService;

//监听kafka消息的类
public class ArticleListener implements MessageListener<String, String> {

  //注入articleService对象，用来保存文章到mysql数据库
  @Autowired
  ArticleService articleService;

  //
  //监听消息的方法
  @Override
  public void onMessage(ConsumerRecord<String, String> data) {
    //获取kafka的jsonString字符串
    String jsonString = data.value();
    if (jsonString.startsWith("articleID")) {
      //在消费端获取文章id,再执行数据库+1操作
      String[] split = jsonString.split("=");
      String id = split[1];
      //从数据库查询文章
      Article article = articleService.selectByPrimaryKey(Integer.parseInt(id));
      article.setHits(article.getHits() + 1);
      //更新到数据库
      articleService.updateByPrimaryKeySelective(article);

    } else {
      Article article = JSON.parseObject(jsonString, Article.class);
      int insertSelective = articleService.insertSelective(article);
    }
  }
}
