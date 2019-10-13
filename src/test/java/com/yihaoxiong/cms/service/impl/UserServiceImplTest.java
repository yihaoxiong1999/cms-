package com.yihaoxiong.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageInfo;
import com.yihaoxiong.cms.domain.User;
import com.yihaoxiong.cms.service.UserService;

public class UserServiceImplTest extends JunitParent {

	
	@Resource
	private UserService userService ;
	@Test
	public void testSelects() {
		
		PageInfo<User> info = userService.selects(null, 0, 3)	;
		List<User> list = info.getList();
		
		System.out.println(list);
		
	}

	@Test
	public void testInsertSelective() {
	}

	@Test
	public void testSelectByPrimaryKey() {
	}

	@Test
	public void testUpdateByPrimaryKeySelective() {
	}

}
