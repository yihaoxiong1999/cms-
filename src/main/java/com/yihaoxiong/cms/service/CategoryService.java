package com.yihaoxiong.cms.service;

import java.util.List;

import com.yihaoxiong.cms.domain.Category;

public interface CategoryService {
  /**
   * 
   * <br>Description:TODO 根据栏目查询其下所有分类
   * <br>Author:衣豪雄(1315712803@qq.com)
   * @param cid
   * @return
   */
  List<Category> selectsByChannelId(Integer cid);
}
