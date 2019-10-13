package com.yihaoxiong.cms.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.yihaoxiong.cms.domain.Article;
import com.yihaoxiong.cms.domain.Comment;
import com.yihaoxiong.cms.domain.User;
import com.yihaoxiong.cms.service.ArticleService;
import com.yihaoxiong.cms.service.CommentService;
import com.yihaoxiong.cms.util.PageUtil;

@RequestMapping("article")
@Controller
public class ArticleController {

  @Resource
  private ArticleService articleService;

  @Resource
  private CommentService commentService;

  /**
   * 
   * <br>Description:TODO 查询所有文章根据点击量倒叙
   * <br>Author:衣豪雄(1315712803@qq.com)
   * <br>Date:2019年9月25日
   * @return
   */
  @GetMapping("my")
  public ModelAndView articleabc() {
    ModelAndView mv = new ModelAndView();
    List<Article> articleabc = articleService.articleabc();
    mv.getModelMap().put("articleabc", articleabc);
    mv.setViewName("artilce.jsp");
    return mv;
  }

  /**
   * 
   * <br>Description:TODO 检查是否有上一篇
   * <br>Author:衣豪雄(1315712803@qq.com)
   * <br>Date:2019年9月24日
   * @param article
   * @return
   */
  @ResponseBody
  @GetMapping("checkPro")
  public boolean checkPro(Article article) {
    Article pro = articleService.selectPro(article);
    return pro != null;
  }

  /**
   * 
   * <br>Description:TODO 检查是否有上一篇
   * <br>Author:衣豪雄(1315712803@qq.com)
   * <br>Date:2019年9月24日
   * @param article
   * @return
   */
  @ResponseBody
  @GetMapping("checkNext")
  public boolean checkNext(Article article) {
    Article next = articleService.selectNext(article);
    return next != null;
  }

  /**
   * 
   * <br>Description:TODO 上一篇
   * <br>Author:衣豪雄(1315712803@qq.com)
   * <br>Date:2019年9月24日
   * @param article
   * @param model
   * @param page
   * @param pageSize
   * @return
   */
  @GetMapping("selectPro")
  public String selectPro(Article article, Model model, @RequestParam(defaultValue = "1") Integer page,
    @RequestParam(defaultValue = "10") Integer pageSize) {

    article.setStatus(1);//这个查询审核过的
    article.setDeleted(0);//不能删除
    ////查询上一篇内容
    Article pro = articleService.selectPro(article);

    //评论
    PageInfo<Comment> info = commentService.selects(pro.getId(), page, pageSize);
    String pages = PageUtil.page(page, info.getPages(), "/article/selectByUser", pageSize);

    model.addAttribute("article", pro);//把上一篇内容放入model
    model.addAttribute("pages", pages);
    model.addAttribute("comments", info.getList());
    return "my/article";
  }

  /**
   * 
   * <br>Description:TODO 下一篇
   * <br>Author:衣豪雄(1315712803@qq.com)
   * <br>Date:2019年9月24日
   * @param article
   * @param model
   * @param page
   * @param pageSize
   * @return
   */
  @GetMapping("selectNext")
  public String selectNext(Article article, Model model, @RequestParam(defaultValue = "1") Integer page,
    @RequestParam(defaultValue = "10") Integer pageSize) {

    article.setStatus(1);//这个查询审核过的
    article.setDeleted(0);//不能删除
    //查询下一篇内容
    Article next = articleService.selectNext(article);

    //评论
    PageInfo<Comment> info = commentService.selects(next.getId(), page, pageSize);
    String pages = PageUtil.page(page, info.getPages(), "/article/selectByUser", pageSize);

    model.addAttribute("article", next);//把下一篇内容放入model
    model.addAttribute("pages", pages);
    model.addAttribute("comments", info.getList());
    return "my/article";
  }

  /**
   * 
   * <br>Description:TODO 添加评论
   * <br>Author:衣豪雄(1315712803@qq.com)
   * <br>Date:2019年9月23日
   * @param comment
   * @param request
   * @return
   */
  @ResponseBody
  @PostMapping("comment")
  public boolean comment(Comment comment, HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    if (null == session)
      return false;
    //评论人
    comment.setUser((User) session.getAttribute("user"));
    comment.setCreated(new Date());
    return commentService.insert(comment) > 0;
  }

  /**
   * 
   * <br>Description:TODO 个人查看单个文章
   * <br>Author:衣豪雄(1315712803@qq.com)
   * @param id
   * @param model
   * @return
   */
  @GetMapping("selectByUser")
  public String selectByUser(Integer id, Model model, @RequestParam(defaultValue = "1") Integer page,
    @RequestParam(defaultValue = "10") Integer pageSize) {
    Article article = articleService.selectByPrimaryKey(id);

    //评论
    PageInfo<Comment> info = commentService.selects(article.getId(), page, pageSize);
    String pages = PageUtil.page(page, info.getPages(), "/article/selectByUser", pageSize);

    model.addAttribute("article", article);
    model.addAttribute("pages", pages);
    model.addAttribute("comments", info.getList());
    return "my/article";
  }

  /**
   * 
   * <br>Description:TODO 个人中心查看文章
   * <br>Author:衣豪雄(1315712803@qq.com)
   * @param model
   * @param article
   * @param page
   * @param pageSize
   * @param request
   * @return
   */
  @GetMapping("selectsByUser")
  public String selectsByUser(Model model, Article article, @RequestParam(defaultValue = "1") Integer page,
    @RequestParam(defaultValue = "3") Integer pageSize, HttpServletRequest request) {

    //个人中心.只能查看自己的文章
    HttpSession session = request.getSession(false);
    if (session == null)
      return "redirect:/passport/login";//session失效,重新登录

    User user = (User) session.getAttribute("user");
    //只能查看自己的文章
    article.setUserId(user.getId());

    PageInfo<Article> info = articleService.selects(article, page, pageSize);

    //动态的拼接url
    String url = "/article/selectsByUser";
    if (article.getTitle() != null) {
      url += "?title=" + article.getTitle();
    }

    String pages = PageUtil.page(page, info.getPages(), "/article/selectsByUser?title=" + article.getTitle(),
      pageSize);
    model.addAttribute("articles", info.getList());
    model.addAttribute("pages", pages);
    model.addAttribute("article", article);

    return "my/articles";

  }

  @Value("${filePath}")
  private String filePath;//文件上传路径

  /**
   * 
   * <br>Description:TODO 发布文章
   * <br>Author:衣豪雄(1315712803@qq.com)
   * @param artice
   * @param file
   * @param request
   * @return
   */
  @ResponseBody
  @PostMapping("publishupdate")
  public boolean publishupdate(Article artice, MultipartFile file, HttpServletRequest request) {

    // 1.上传文章标题图片
    if (!file.isEmpty()) {

      // 获取原始上传文件的名称//a.jpg
      String originalFilename = file.getOriginalFilename();
      // 为了防止图片名称重复.使用UUID 统一处理上传的名称名称
      String newFilename = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
      // 文件上传路径
      // String path="d:/pic/";
      File file2 = new File(filePath + newFilename);

      try {
        // 把文件写入硬盘
        file.transferTo(file2);

        // 封装标题图片的地址
        artice.setPicture(newFilename);
      } catch (IllegalStateException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }

    }

    // 2.把文章内容保存到数据库
    // 默认文章的基本属性

    artice.setUpdated(new Date());// 文章修改时间
    // 发布人
    // false:从request获取session.对象,如果没有则返回null.如果有则返回session..
    HttpSession session = request.getSession(false);
    if (session != null) {
      User user = (User) session.getAttribute("user");
      artice.setUserId(user.getId());// 发布人
    } else {
      return false;// 没有登录.不能发布
    }
    return articleService.updateByPrimaryKeySelective(artice) > 0;

  }

  /**
  * 
  * <br>Description:TODO 去发布文章页面
  * <br>Author:衣豪雄(1315712803@qq.com)
  * @return
  */
  @GetMapping("publish")
  public String publish() {

    return "my/publish";
  }

  /**
   * 
   * <br>Description:TODO 管理员查看文章
   * <br>Author:衣豪雄(1315712803@qq.com)
   * @param model
   * @param article
   * @param page
   * @param pageSize
   * @return
   */
  @RequestMapping("selectsByAdmin")
  public String selects(Model model, Article article, @RequestParam(defaultValue = "1") Integer page,
    @RequestParam(defaultValue = "3") Integer pageSize) {

    if (article.getStatus() == null || article.getStatus().equals(""))
      article.setStatus(0);//默认待审核状态

    PageInfo<Article> info = articleService.selects(article, page, pageSize);
    String pages = PageUtil.page(page, info.getPages(),
      "/article/selectsByAdmin?status=" + article.getStatus(), pageSize);
    model.addAttribute("articles", info.getList());
    model.addAttribute("pages", pages);
    model.addAttribute("article", article);

    return "admin/articles";

  }

  /**
   * 
   * <br>Description:TODO 管理员查看单个文章
   * <br>Author:衣豪雄(1315712803@qq.com)
   * @param id
   * @param model
   * @return
   */
  @GetMapping("selectByAdmin")
  public String select(Integer id, Model model) {
    Article article = articleService.selectByPrimaryKey(id);

    model.addAttribute("article", article);
    return "admin/article";

  }

  /**
   * 
   * <br>Description:TODO 去文章修改页面
   * <br>Author:衣豪雄(1315712803@qq.com)
   * <br>Date:2019年9月23日
   * @return
   */
  @GetMapping("update")
  public String update(Integer id, Model model) {
    Article article = articleService.selectByPrimaryKey(id);

    model.addAttribute("article", article);
    return "my/articleupdate";
  }

  /**
  * 
  * <br>Description:TODO 更新文章--审核文章,删除文章
  * <br>Author:衣豪雄(1315712803@qq.com)
  * @param article
  * @return
  */
  @ResponseBody
  @PostMapping("update")
  public boolean update(Article article) {
    return articleService.updateByPrimaryKeySelective(article) > 0;
  }

}
