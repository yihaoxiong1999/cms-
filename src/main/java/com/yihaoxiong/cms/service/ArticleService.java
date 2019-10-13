package com.yihaoxiong.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.yihaoxiong.cms.domain.Article;

public interface ArticleService {
  /**
   * 
   * <br>Description:TODO 文章列表查询
   * <br>Author:衣豪雄(1315712803@qq.com)
   * @param article
   * @param page
   * @param pageSize
   * @return
   */
  PageInfo<Article> selects(Article article, Integer page, Integer pageSize);

  /**
   * 
   * <br>Description:TODO 查询所有文章根据点击量倒叙
   * <br>Author:衣豪雄(1315712803@qq.com)
   * <br>Date:2019年9月25日
   * @return
   */
  List<Article> articleabc();

  /**
   * 
   * <br>Description:TODO 查询所有文章根据评论的数量正序
   * <br>Author:衣豪雄(1315712803@qq.com)
   * <br>Date:2019年9月25日
   * @return
   */
  /* List<Article> articleab();
  */
  int insertSelective(Article record);

  /**
  * 
  * <br>Description:TODO 根据ID 查询单个文章
  * <br>Author:衣豪雄(1315712803@qq.com)
  * @param id
  * @return
  */
  Article selectByPrimaryKey(Integer id);

  /**
   * 
   * <br>Description:TODO 下一篇
   * <br>Author:衣豪雄(1315712803@qq.com)
   * <br>Date:2019年9月24日
   * @param record
   * @return
   */
  Article selectNext(Article article);

  /**
   * 
   * <br>Description:TODO 上一篇
   * <br>Author:衣豪雄(1315712803@qq.com)
   * <br>Date:2019年9月24日
   * @param record
   * @return
   */
  Article selectPro(Article article);

  int updateByPrimaryKeySelective(Article record);

}
