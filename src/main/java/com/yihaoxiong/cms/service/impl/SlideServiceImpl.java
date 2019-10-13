package com.yihaoxiong.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yihaoxiong.cms.dao.SlideMapper;
import com.yihaoxiong.cms.domain.Slide;
import com.yihaoxiong.cms.service.SlideService;

@Service
public class SlideServiceImpl implements  SlideService {
	@Resource
	private SlideMapper slideMapper;

	

	@Override
	public List<Slide> selects() {
		// TODO Auto-generated method stub
		return slideMapper.selects();
	}

}
