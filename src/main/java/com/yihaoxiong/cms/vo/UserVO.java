package com.yihaoxiong.cms.vo;

import com.yihaoxiong.cms.domain.User;

public class UserVO extends User {
	
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 4583054591399546760L;
	private String repassword;//重复密码

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	

}
