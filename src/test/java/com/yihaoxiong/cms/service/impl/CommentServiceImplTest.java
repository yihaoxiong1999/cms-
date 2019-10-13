package com.yihaoxiong.cms.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.github.pagehelper.PageInfo;
import com.yihaoxiong.cms.domain.Article;
import com.yihaoxiong.cms.domain.Comment;
import com.yihaoxiong.cms.domain.User;
import com.yihaoxiong.cms.service.ArticleService;
import com.yihaoxiong.cms.service.CommentService;
import com.yihaoxiong.common.utils.DateUtil;
import com.yihaoxiong.common.utils.RandomUtils;
import com.yihaoxiong.common.utils.StringUtil;

public class CommentServiceImplTest extends JunitParent {

  @Resource
  private CommentService commentService;

  @Resource
  private ArticleService articleService;

  @Test
  public void testInsert() {
    //1.评论人
    User user = new User();
    user.setId(128);
    //评论文章
    Article a = new Article();
    a.setStatus(1);//只能给审核过的文章评论
    a.setDeleted(0);//给没有删除的文章评论
    //查询出 十二篇文章
    PageInfo<Article> info = articleService.selects(a, 1, 4);
    List<Article> list = info.getList();

    for (int i = 0; i < 1000; i++) {
      Comment comment = new Comment();
      //评论人
      comment.setUser(user);

      //随机从list集合中获取文章对象
      int j = RandomUtils.random(0, list.size() - 2);
      Article article2 = list.get(j);
      //1.评论的文章
      comment.setArticle(article2);
      //获取随机时间时间
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      Date date = null;
      try {
        date = DateUtil.randomDate(df.parse("2019-01-01 00:00:00"), new Date());
      } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      //3.评论的日期
      comment.setCreated(date);
      //4.评论的内容
      //随机产生150字
      String string = StringUtil.randomChineseString(150);
      comment.setContent(string);
      //5.执行插入
      commentService.insert(comment);
    }
  }

}
