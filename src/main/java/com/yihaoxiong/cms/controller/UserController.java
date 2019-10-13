package com.yihaoxiong.cms.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.yihaoxiong.cms.domain.User;
import com.yihaoxiong.cms.service.UserService;
import com.yihaoxiong.cms.util.PageUtil;

/**
 * 
 * <br>Title:TODO UserController
 * <br>Description:TODO 用户模块
 * <br>Author:衣豪雄(1315712803@qq.com)
 */
@RequestMapping("user")
@Controller
public class UserController {

  @Resource
  private UserService userService;

  /**
   * 
   * <br>Description:TODO 用户列表
   * <br>Author:衣豪雄(1315712803@qq.com)
   * @param model
   * @param username
   * @param page
   * @param pageSize
   * @return
   */
  @RequestMapping("selects")
  public String selects(Model model, @RequestParam(defaultValue = "") String username,
    @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "3") Integer pageSize) {
    PageInfo<User> info = userService.selects(username, page, pageSize);
    //分页
    String pages = PageUtil.page(page, info.getPages(), "/user/selects?username=" + username, pageSize);

    model.addAttribute("users", info.getList());//封装结果集
    model.addAttribute("username", username);//封装查询条件
    model.addAttribute("pages", pages);//封装分页

    return "admin/users";
  }

  //更新用户状态
  @ResponseBody
  @PostMapping("update")
  public boolean update(User user) {
    return userService.updateByPrimaryKeySelective(user) > 0;
  }
}
