package com.yihaoxiong.cms.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * <br>Title:TODO 类标题
 * <br>Description:TODO 个人中心后台拦截器
 * <br>Author:衣豪雄(1315712803@qq.com)
 */
public class UserInterceptor extends HandlerInterceptorAdapter {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    throws Exception {
    //判断是否已经登录

    HttpSession session = request.getSession();
    //从session获取登录的对象
    Object object = session.getAttribute("user");
    if (null != object)//用户已经登录
      return true;//放行
    //没有登录.重定向到登录页面
    response.sendRedirect("/passport/login");

    return false;
  }

}
