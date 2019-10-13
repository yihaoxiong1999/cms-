package com.yihaoxiong.cms.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihaoxiong.cms.domain.Channel;
import com.yihaoxiong.cms.service.ChannelService;

/**
 * 
 * <br>Title:TODO  栏目controller
 * <br>Description:TODO 类功能描述
 * <br>Author:衣豪雄(1315712803@qq.com)
 */
@RequestMapping("channel")
@Controller
public class ChannelController {
  @Resource
  private ChannelService channelService;

  @ResponseBody
  @GetMapping("selects")
  public List<Channel> selects() {
    return channelService.selects();
  }

}
