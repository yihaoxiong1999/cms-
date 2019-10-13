package com.yihaoxiong.cms.dao;

import java.util.List;

import com.yihaoxiong.cms.domain.Category;

public interface CategoryMapper {
  /**
   * 
   * <br>Description:TODO 根据栏目查询其下所有分类
   * <br>Author:衣豪雄(1315712803@qq.com)
   * @param cid
   * @return
   */
  List<Category> selectsByChannelId(Integer cid);

  int deleteByPrimaryKey(Integer id);

  int insert(Category record);

  int insertSelective(Category record);

  Category selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(Category record);

  int updateByPrimaryKey(Category record);
}