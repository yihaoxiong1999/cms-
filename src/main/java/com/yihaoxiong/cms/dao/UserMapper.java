package com.yihaoxiong.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yihaoxiong.cms.domain.User;

/**
 * 
 * <br>Title:TODOUserMapper
 * <br>Description:TODO 类功能描述
 * <br>Author:衣豪雄(1315712803@qq.com)
 */
public interface UserMapper {
  /**
   * 
   * <br>Description:TODO 用户列表查询
   * <br>Author:衣豪雄(1315712803@qq.com)
   * @param username
   * @return
   */
  List<User> selects(@Param("username") String username);

  /**
   * 
   * <br>Description:TODO 根据用户查询用户
   * <br>Author:衣豪雄(1315712803@qq.com)
   * @param username
   * @return
   */
  User selectByUsername(String username);

  int deleteByPrimaryKey(Integer id);

  int insert(User record);

  int insertSelective(User record);

  User selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(User record);

  int updateByPrimaryKey(User record);
}