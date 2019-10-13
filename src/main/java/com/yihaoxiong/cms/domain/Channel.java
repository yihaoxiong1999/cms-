package com.yihaoxiong.cms.domain;

import java.io.Serializable;

/**
 * 
 * <br>Title:TODO 类标题
 * <br>Description:TODO 栏目
 * <br>Author:衣豪雄(1315712803@qq.com)
 * <br>Date:2019年9月21日
 */
public class Channel implements Serializable {

  /**
   * <br>Description:TODO 变量描述
   * <br>Author:衣豪雄(1315712803@qq.com)
   * <br>Date:2019年9月21日
   */
  private static final long serialVersionUID = -3606060599347286276L;

  private Integer id;

  private String name;

  private String description;

  private String icon;

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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description == null ? null : description.trim();
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon == null ? null : icon.trim();
  }

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
    Channel other = (Channel) obj;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    return true;
  }

}