package com.yihaoxiong.cms.dao;

import java.util.List;

import com.yihaoxiong.cms.domain.Channel;

/**
 * 
 * <br>Title:TODO ChannelMapper
 * <br>Description:TODO 栏目
 * <br>Author:衣豪雄(1315712803@qq.com)
 */
public interface ChannelMapper {
  /**
   * 
   * <br>Description:TODO 所有的栏目
   * <br>Author:衣豪雄(1315712803@qq.com)
   * @return
   */
  List<Channel> selects();

  int deleteByPrimaryKey(Integer id);

  int insert(Channel record);

  int insertSelective(Channel record);

  Channel selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(Channel record);

  int updateByPrimaryKey(Channel record);
}