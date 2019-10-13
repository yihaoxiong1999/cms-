package com.yihaoxiong.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yihaoxiong.cms.dao.ChannelMapper;
import com.yihaoxiong.cms.domain.Channel;
import com.yihaoxiong.cms.service.ChannelService;
@Service
public class ChannelServiceImpl implements ChannelService {
	@Resource
	private ChannelMapper channelMapper ;

	@Override
	public List<Channel> selects() {
		return channelMapper.selects();
	}

}
