package com.yihaoxiong.cms.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.yihaoxiong.cms.domain.Article;
import com.yihaoxiong.cms.domain.Category;
import com.yihaoxiong.cms.domain.Channel;
import com.yihaoxiong.cms.domain.Slide;
import com.yihaoxiong.cms.service.ArticleService;
import com.yihaoxiong.cms.service.CategoryService;
import com.yihaoxiong.cms.service.ChannelService;
import com.yihaoxiong.cms.service.SlideService;
import com.yihaoxiong.cms.util.PageUtil;

/**
 * 
 * -<br>Title:TODO IndexController
 * <br>Description:TODO 类功能描述
 * <br>Author:衣豪雄(1315712803@qq.com)
 */
@SuppressWarnings("rawtypes")
@RequestMapping(value = { "index", "", "/" })
@Controller
public class IndexController {

  @Resource
  private ChannelService channelService;

  @Resource
  private SlideService slideService;

  @Resource
  private CategoryService categoryService;

  @Resource
  private ArticleService articleService;

  @Resource
  private RedisTemplate redisTemplate;

  //进入系统首页
  @GetMapping(value = "")
  public String index(Model model, Article article, @RequestParam(defaultValue = "1") Integer page,
    @RequestParam(defaultValue = "5") Integer pageSize) {

    //0设置查询条件
    article.setStatus(1);//查询审过的文章
    article.setDeleted(0);//查询没有被删除的文章
    //1查询出所有栏目
    List<Channel> channels = channelService.selects();
    model.addAttribute("channels", channels);
    //2.如果栏目为null 则查询热门文章
    if (article.getChannelId() == null) {

      //广告数据--轮播图
      List<Slide> selects = slideService.selects();
      model.addAttribute("slides", selects);

      //这是专高一的代码 显示热点文章
      article.setHot(1);//设置查询条件
      /*PageInfo<Article> info = articleService.selects(article, page, pageSize);
      model.addAttribute("hotArticles", info.getList());
      String pages = PageUtil.page(page, info.getPages(), "/", pageSize);
      model.addAttribute("pages", pages);*/

      //显示热点文章：专高2的代码
      //1.从redis中查询
      List<Article> redisDB = (List<Article>) redisTemplate.opsForValue().get("HotArticles");
      //2.判断redis查询出来的是否为空
      if (redisDB != null && redisDB.size() > 0) {
        System.out.println("从Redis中查询出来的热点文章" + "**********");
        //3.如果不为空,直接把数据响应给前台  
        model.addAttribute("hotArticles", redisDB);
      } else {
        //4.如果为空，从mysql中查,查询出来后,存入Redis,  一直循环
        PageInfo<Article> info1 = articleService.selects(article, page, pageSize);
        System.out.println("从MySQL中查询出来的热点文章" + "**********");
        redisTemplate.opsForValue().set("HotArticles", info1.getList());
        String pages = PageUtil.page(page, info1.getPages(), "/", pageSize);
        model.addAttribute("hotArticles", info1.getList());
        model.addAttribute("pages", pages);
      }

    }

    //3.如果栏目不为null 则查询栏目下的分类及文章
    if (article.getChannelId() != null) {
      //查询栏目下所有的分类
      List<Category> categorys = categoryService.selectsByChannelId(article.getChannelId());
      model.addAttribute("categorys", categorys);
      //栏目或分类下 的文章
      PageInfo<Article> info = articleService.selects(article, page, pageSize);
      String pages = PageUtil.page(page, info.getPages(), "/", pageSize);
      model.addAttribute("articles", info.getList());
      model.addAttribute("pages", pages);

    }
    //封装的查询条件
    model.addAttribute("article", article);

    //3.最新文章.--按照文章发布日期倒序显示最近的10篇文章
    Article article2 = new Article();
    article2.setStatus(1);//只显示审核过的文章
    article2.setDeleted(0);//查询没有被删除的文章
    /*PageInfo<Article> info2 = articleService.selects(article2, 1, 10);
    model.addAttribute("lastArticles", info2.getList());*/

    //专高2的代码
    //从redis中查询
    List<Article> redisNewArticles = (List<Article>) redisTemplate.opsForValue().get("NewArticles");
    //判断redis中的数据是否为空
    if (redisNewArticles != null && redisNewArticles.size() > 0) {
      System.out.println("从redis中查询了最新文章。。。。");
      //不是null
      model.addAttribute("lastArticles", redisNewArticles);
    } else {
      System.out.println("从MySQL中查询了最新文章。。。。。");
      PageInfo<Article> info2 = articleService.selects(article2, 1, 10);
      redisTemplate.opsForValue().set("NewArticles", info2.getList());
      model.addAttribute("lastArticles", info2.getList());
    }

    return "index/index";

  }

}
