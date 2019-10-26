package com.yihaoxiong.cms.dao;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.yihaoxiong.cms.domain.Article;

//具备了curd 的方法
public interface ArticleRepository extends ElasticsearchRepository<Article, Integer> {

  List<Article> findByTitle(String key);
}
