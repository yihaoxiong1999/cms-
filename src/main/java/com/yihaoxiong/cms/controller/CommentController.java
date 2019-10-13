package com.yihaoxiong.cms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.yihaoxiong.cms.domain.Comment;
import com.yihaoxiong.cms.domain.User;
import com.yihaoxiong.cms.service.CommentService;
import com.yihaoxiong.cms.util.PageUtil;

@RequestMapping("comment")
@Controller
public class CommentController {

  @Resource
  private CommentService commentService;

  @GetMapping("selects")
  public String selects(Model model, HttpServletRequest request, Integer userId,
    @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "3") Integer pageSize) {
    HttpSession session = request.getSession(false);
    if (null == session) {
      return "redirect:/passport/login";//没有登陆或登陆失效
    }
    User user = (User) session.getAttribute("user");

    PageInfo<Comment> info = commentService.selectsByUserId(user.getId(), page, pageSize);
    String pages = PageUtil.page(page, info.getPages(), "/comment/selects", pageSize);
    model.addAttribute("comments", info.getList());
    model.addAttribute("pages", pages);
    return "my/comments";

  }
}
