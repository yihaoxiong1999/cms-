package com.yihaoxiong.cms.domain;

import java.io.Serializable;

/**
 * 
 * <br>Title:TODO 类标题
 * <br>Description:TODO 系统设置
 * <br>Author:衣豪雄(1315712803@qq.com)
 * <br>Date:2019年9月21日
 */
public class Settings implements Serializable {

  /**
   * <br>Description:TODO 变量描述
   * <br>Author:衣豪雄(1315712803@qq.com)
   * <br>Date:2019年9月21日
   */
  private static final long serialVersionUID = -7073830004785473302L;

  private Integer id;

  private String siteDomain;

  private String siteName;

  private Integer articleListSize;

  private Integer slideSize;

  private String adminUsername;

  private String adminPassword;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getSiteDomain() {
    return siteDomain;
  }

  public void setSiteDomain(String siteDomain) {
    this.siteDomain = siteDomain == null ? null : siteDomain.trim();
  }

  public String getSiteName() {
    return siteName;
  }

  public void setSiteName(String siteName) {
    this.siteName = siteName == null ? null : siteName.trim();
  }

  public Integer getArticleListSize() {
    return articleListSize;
  }

  public void setArticleListSize(Integer articleListSize) {
    this.articleListSize = articleListSize;
  }

  public Integer getSlideSize() {
    return slideSize;
  }

  public void setSlideSize(Integer slideSize) {
    this.slideSize = slideSize;
  }

  public String getAdminUsername() {
    return adminUsername;
  }

  public void setAdminUsername(String adminUsername) {
    this.adminUsername = adminUsername == null ? null : adminUsername.trim();
  }

  public String getAdminPassword() {
    return adminPassword;
  }

  public void setAdminPassword(String adminPassword) {
    this.adminPassword = adminPassword == null ? null : adminPassword.trim();
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
    Settings other = (Settings) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

}