package com.yihaoxiong.cms.service.impl;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.yihaoxiong.cms.domain.Article;
import com.yihaoxiong.cms.domain.Category;
import com.yihaoxiong.cms.service.ArticleService;
import com.yihaoxiong.cms.service.CategoryService;
import com.yihaoxiong.common.utils.DateUtil;
import com.yihaoxiong.common.utils.RandomUtils;
import com.yihaoxiong.common.utils.StreamUtil;

public class ArticleServiceImplTest extends JunitParent {
  @Resource
  private CategoryService categoryService;

  @Resource
  private ArticleService articleService;

  @Test
  public void testSelects() {
  }

  //读取文本文件 插入文章数据
  @Test
  public void testInsertSelective() {

    //读取月考文章的目录
    File file = new File("D:\\爬虫");
    //获取里边的所有文章
    File[] listFiles = file.listFiles();
    //循环遍历文章
    for (File file2 : listFiles) {
      //读取文章的内容
      String content = StreamUtil.readTextFile(file2);
      Article article = new Article();
      //文件名包含后缀
      String filename = file2.getName();
      //把文件的后缀名称去掉
      String title = filename.substring(0, filename.indexOf("."));
      //文章的标题
      article.setTitle(title);
      //文章的内容
      article.setContent(content);
      //文章摘要
      article.setSummary(content);
      //随机设置点击量，热门，分类
      //点击量
      int hits = RandomUtils.random(0, 10000);
      article.setHits(hits);
      //是否热门
      int hot = RandomUtils.random(0, 1);
      article.setHot(hot);
      //随机分类
      int channelId = RandomUtils.random(1, 9);
      article.setChannelId(channelId);
      //根据栏目Id查询对应的分类集合
      List<Category> list = categoryService.selectsByChannelId(channelId);
      //随机获取
      Category category = list.get(RandomUtils.random(0, list.size() - 1));
      article.setCategoryId(article.getId());//分类
      //文章的发布日期从2018-01-01设置，模拟到今天设置随机
      Calendar calendar = Calendar.getInstance();
      //初始化设置随机时间，从2018年开始
      calendar.set(2018, 0, 1, 0, 0, 0);
      //发布文章的时间
      article.setCreated(DateUtil.randomDate(calendar.getTime(), new Date()));
      //发布人
      article.setUserId(128);
      //已审核
      article.setStatus(1);
      //未删除
      article.setDeleted(0);
      //执行插入
      articleService.insertSelective(article);
    }

  }

  @Test
  public void testSelectByPrimaryKey() {
  }

  @Test
  public void testUpdateByPrimaryKeyWithBLOBs() {
  }

}
