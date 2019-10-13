package com.yihaoxiong.cms.service;

import com.github.pagehelper.PageInfo;
import com.yihaoxiong.cms.domain.Comment;

public interface CommentService {
  /**
   * 
   * <br>Description:TODO 查询所有评论
   * <br>Author:衣豪雄(1315712803@qq.com)
   * <br>Date:2019年9月22日
   * @return
   */
  PageInfo<Comment> selects(Integer articleId, Integer page, Integer pageSize);

  /**
   * 
   * <br>Description:TODO 根据用户查询
   * <br>Author:衣豪雄(1315712803@qq.com)
   * <br>Date:2019年9月23日
   * @param comment
   * @return
   */
  PageInfo<Comment> selectsByUserId(Integer userId, Integer page, Integer pageSize);

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
