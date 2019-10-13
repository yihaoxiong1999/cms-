package com.yihaoxiong.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * <br>Title:TODO 管理员后台控制器
 * <br>Description:TODO 类功能描述
 * <br>Author:衣豪雄(1315712803@qq.com)
 */
@RequestMapping("admin")
@Controller
public class AdminController {

  /**
   * 
   * <br>Description:TODO 进入管理员后台首页
   * <br>Author:衣豪雄(1315712803@qq.com)
   * @return
   */
  @RequestMapping(value = { "index", "/", "" })
  public String index() {
    return "admin/index";
  }

}
