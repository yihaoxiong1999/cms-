package com.yihaoxiong.cms.domain;

import java.io.Serializable;

/**
 * 
 * <br>Title:TODO 类标题
 * <br>Description:TODO 广告
 * <br>Author:衣豪雄(1315712803@qq.com)
 * <br>Date:2019年9月21日
 */
public class Slide implements Serializable {

  /**
   * <br>Description:TODO 变量描述
   * <br>Author:衣豪雄(1315712803@qq.com)
   * <br>Date:2019年9月21日
   */
  private static final long serialVersionUID = -7360712494454166280L;

  private Integer id;

  private String title;

  private String picture;

  private String url;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title == null ? null : title.trim();
  }

  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture == null ? null : picture.trim();
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url == null ? null : url.trim();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Slide other = (Slide) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

}