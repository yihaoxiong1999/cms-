package com.yihaoxiong.cms.service.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihaoxiong.cms.dao.ArticleRepository;
import com.yihaoxiong.cms.domain.Article;
import com.yihaoxiong.cms.service.ArticleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class ImportMysqlDB2Es {

  @Autowired
  ArticleService articleService;

  //注入一个
  @Autowired
  ArticleRepository articleRepository;

  @Test
  public void testImport2Es() {
    //1.从mysql中查询文章，
    List<Article> findAll = articleService.findAll();

    //2.保存到es
    articleRepository.saveAll(findAll);
  }
}
