package com.yihaoxiong.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * <br>Title:TODO MyController
 * <br>Description:TODO 类功能描述
 * <br>Author:衣豪雄(1315712803@qq.com)
 */
@RequestMapping("my")
@Controller
public class MyController {

  /**
   * 
   * <br>Description:TODO 个人中心的首页
   * <br>Author:衣豪雄(1315712803@qq.com)
   * @return
   */
  @GetMapping({ "/", "", "/index" })
  public String index() {

    return "my/index";

  }
}
