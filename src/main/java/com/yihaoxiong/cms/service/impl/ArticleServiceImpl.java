package com.yihaoxiong.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yihaoxiong.cms.dao.ArticleMapper;
import com.yihaoxiong.cms.domain.Article;
import com.yihaoxiong.cms.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {
  @Resource
  private ArticleMapper articleMapper;

  @Override
  public PageInfo<Article> selects(Article article, Integer page, Integer pageSize) {
    PageHelper.startPage(page, pageSize);
    List<Article> list = articleMapper.selects(article);
    return new PageInfo<Article>(list);
  }

  @Override
  public int insertSelective(Article record) {
    return articleMapper.insertSelective(record);
  }

  @Override
  public Article selectByPrimaryKey(Integer id) {
    // TODO Auto-generated method stub
    return articleMapper.selectByPrimaryKey(id);
  }

  @Override
  public int updateByPrimaryKeySelective(Article record) {
    // TODO Auto-generated method stub
    return articleMapper.updateByPrimaryKeySelective(record);
  }

  @Override
  public Article selectNext(Article article) {
    // TODO Auto-generated method stub
    return articleMapper.selectNext(article);
  }

  @Override
  public Article selectPro(Article article) {
    // TODO Auto-generated method stub
    return articleMapper.selectPro(article);
  }

  /**
   * 
   * <br>Description:TODO 查询所有文章根据点击量倒叙
   * <br>Author:衣豪雄(1315712803@qq.com)
   * <br>Date:2019年9月25日
   * @see com.yihaoxiong.cms.service.ArticleService#articleabc()
   */
  @Override
  public List<Article> articleabc() {
    // TODO Auto-generated method stub
    return articleMapper.articleabc();
  }

  /**
   * 
   * <br>Description:TODO 查询所有文章根据评论的数量正序
   * <br>Author:衣豪雄(1315712803@qq.com)
   * <br>Date:2019年9月25日
   * @return
   */
  /* @Override
  public List<Article> articleab() {
    // TODO Auto-generated method stub
    return articleMapper.articleab();
  }*/

}
