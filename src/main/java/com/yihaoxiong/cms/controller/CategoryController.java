package com.yihaoxiong.cms.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihaoxiong.cms.domain.Category;
import com.yihaoxiong.cms.service.CategoryService;

/**
 * 
 * <br>Title:TODO 分类controller
 * <br>Description:TODO 类功能描述
 * <br>Author:衣豪雄(1315712803@qq.com)
 */
@RequestMapping("category")
@Controller
public class CategoryController {
  @Resource
  private CategoryService categoryService;

  @ResponseBody
  @GetMapping("selects")
  public List<Category> selects(Integer cid) {
    return categoryService.selectsByChannelId(cid);
  }

}
