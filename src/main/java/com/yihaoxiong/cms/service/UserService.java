package com.yihaoxiong.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.yihaoxiong.cms.domain.User;
import com.yihaoxiong.cms.vo.UserVO;

/**
 * 
 * <br>Title:TODO 类标题
 * <br>Description:TODO 类功能描述
 * <br>Author:衣豪雄(1315712803@qq.com)
 */
public interface UserService {

  /**
   * 
   * <br>Description:TODO 用户列表小二
   * <br>Author:衣豪雄(1315712803@qq.com)
   * <br>Date:2019年10月23日
   * @return
   */
  List<User> findAll();

  /**
   * 
   * <br>Description:TODO  用户列表查询
   * <br>Author:衣豪雄(1315712803@qq.com)
   * @param name
   * @param page
   * @param pageSize
   * @return
   */
  PageInfo<User> selects(String name, Integer page, Integer pageSize);

  /**
   * 
   * <br>Description:TODO 注册用户
   * <br>Author:衣豪雄(1315712803@qq.com)
   * @param record
   * @return
   */
  int insertSelective(UserVO record);

  /**
   * 
   * <br>Description:TODO 单查用户
   * <br>Author:衣豪雄(1315712803@qq.com)
   * @param id
   * @return
   */
  User selectByPrimaryKey(Integer id);

  /**
   * 
   * <br>Description:TODO 修改用户信息
   * <br>Author:衣豪雄(1315712803@qq.com)
   * @param record
   * @return
   */
  int updateByPrimaryKeySelective(User record);

  /**
  * 
  * <br>Description:TODO 登录
  * <br>Author:衣豪雄(1315712803@qq.com)
  * @param user
  * @return
  */
  User login(User user);

}
