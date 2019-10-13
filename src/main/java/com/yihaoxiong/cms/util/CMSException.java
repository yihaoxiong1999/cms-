package com.yihaoxiong.cms.util;

/**
 * 
 * <br>Title:TODO 类标题
 * <br>Description:TODO CMS的自定义异常
 * <br>Author:衣豪雄(1315712803@qq.com)
 */
public class CMSException extends RuntimeException {

  /**
   * <br>Description:TODO 变量描述
   * <br>Author:衣豪雄(1315712803@qq.com)
   * <br>Date:2019年9月21日
   */
  private static final long serialVersionUID = -1591816902425873043L;

  public CMSException() {
  }

  public CMSException(String message) {
    super(message);
  }

}
