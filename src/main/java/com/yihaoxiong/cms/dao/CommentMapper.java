package com.yihaoxiong.cms.dao;

import java.util.List;

import com.yihaoxiong.cms.domain.Comment;

/**
 * 
 * <br>Title:TODO CommentMapper
 * <br>Description:TODO 评论
 * <br>Author:衣豪雄(1315712803@qq.com)
 * <br>Date:2019年9月22日
 */
public interface CommentMapper {

  /**
   * 
   * <br>Description:TODO 查询导入的所有评论
   * <br>Author:衣豪雄(1315712803@qq.com)
   * <br>Date:2019年9月25日
   * @param articleId
   * @return
   */
  List<Comment> selects(Integer articleId);

  /**
   * 
   * <br>Description:TODO 根据用户查询
   * <br>Author:衣豪雄(1315712803@qq.com)
   * <br>Date:2019年9月23日
   * @param comment
   * @return
   */
  List<Comment> selectsByUserId(Integer userId);

  /**
   * 
   * <br>Description:TODO 评论功能开发(添加评论)
   * <br>Author:衣豪雄(1315712803@qq.com)
   * <br>Date:2019年9月25日
   * @param comment
   * @return
   */
  Integer insert(Comment comment);

}
