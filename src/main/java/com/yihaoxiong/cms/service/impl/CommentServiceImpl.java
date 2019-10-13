package com.yihaoxiong.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yihaoxiong.cms.dao.CommentMapper;
import com.yihaoxiong.cms.domain.Comment;
import com.yihaoxiong.cms.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

  @Resource
  private CommentMapper commentMapper;

  @Override
  public PageInfo<Comment> selects(Integer articleId, Integer page, Integer pageSize) {
    Page<Comment> page1 = PageHelper.startPage(page, pageSize);
    List<Comment> list = commentMapper.selects(articleId);
    PageInfo<Comment> pageInfo = new PageInfo<Comment>(list);
    return pageInfo;
  }

  @Override
  public PageInfo<Comment> selectsByUserId(Integer userId, Integer page, Integer pageSize) {
    PageHelper.startPage(page, pageSize);
    List<Comment> list1 = commentMapper.selectsByUserId(userId);
    PageInfo<Comment> pageInfo = new PageInfo<Comment>(list1);
    return pageInfo;
  }

  /**
   * 
   * <br>Description:TODO 评论功能开发(添加评论)
   * <br>Author:衣豪雄(1315712803@qq.com)
   * <br>Date:2019年9月25日
   * @see com.yihaoxiong.cms.service.CommentService#insert(com.yihaoxiong.cms.domain.Comment)
   */
  @Override
  public Integer insert(Comment comment) {
    // TODO Auto-generated method stub
    return commentMapper.insert(comment);
  }

}
