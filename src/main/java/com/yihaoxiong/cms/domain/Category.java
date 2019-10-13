package com.yihaoxiong.cms.domain;

import java.io.Serializable;

/**
 * 
 * <br>Title:TODO 类标题
 * <br>Description:TODO 分类
 * <br>Author:衣豪雄(1315712803@qq.com)
 * <br>Date:2019年9月21日
 */
public class Category implements Serializable {

  /**
   * <br>Description:TODO 变量描述
   * <br>Author:衣豪雄(1315712803@qq.com)
   * <br>Date:2019年9月21日
   */
  private static final long serialVersionUID = 5443420871036655652L;

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
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
    Category other = (Category) obj;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    return true;
  }

  private Integer id;

  private String name;

  private Integer channelId;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name == null ? null : name.trim();
  }

  public Integer getChannelId() {
    return channelId;
  }

  public void setChannelId(Integer channelId) {
    this.channelId = channelId;
  }
}