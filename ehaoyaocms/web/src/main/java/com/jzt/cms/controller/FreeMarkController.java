package com.jzt.cms.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jzt.cms.service.FreeMarkService;

@Controller
@RequestMapping("/freemark")
public class FreeMarkController {

	@Autowired
	private FreeMarkService freeMarkService;

	@RequestMapping("/build")
	public String hi(Map<String, Object> model) {
		model.put("time", new Date());
		model.put("message", "这是测试的内容。。。");
		model.put("toUserName", "张三");
		model.put("fromUserName", "喻适");

		freeMarkService.buildHtml("/welcome.ftl", "/welcome.htm", model);

		return "welcome"; // 自动寻找resources/templates中名字为welcome.ftl/welcome.vm的文件作为模板，拼装后返回
	}

}