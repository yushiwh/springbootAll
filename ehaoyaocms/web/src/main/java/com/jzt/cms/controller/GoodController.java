package com.jzt.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jzt.cms.model.ActivityGood;
import com.jzt.cms.service.ActivityGoodService;
import com.jzt.cms.util.Constant;
import com.jzt.cms.util.PageUtil;

@Controller
public class GoodController {

	@Autowired
	private ActivityGoodService activityGoodService;
	
	@GetMapping("/admin/good_{pageCurrent}_{pageSize}_{pageCount}")
	public String goodList(ActivityGood activityGood,String module_name,@PathVariable Integer pageCurrent,@PathVariable Integer pageSize,@PathVariable Integer pageCount,Model model){
		activityGood.setModule_name(module_name);
		//判断
		if(pageSize == 0){
			pageSize = 10;
		}
		if(pageCurrent == 0){
			pageCurrent = 1;
		}
		int rows = activityGoodService.count(activityGood);
		if(pageCount == 0){
			pageCount = rows%pageSize == 0 ? (rows/pageSize) : (rows/pageSize) + 1;
		}
		
		//查询
		activityGood.setStart((pageCurrent - 1)*pageSize);
		activityGood.setEnd(pageSize);
		if(activityGood.getOrderBy()==null){
			activityGood.setOrderBy(Constant.OrderByAddDateDesc);
		}
		List<ActivityGood> goodList = activityGoodService.list(activityGood);
		
		//输出
		model.addAttribute("goodList", goodList);
		String pageHTML = PageUtil.getPageContent("activity_{pageCurrent}_{pageSize}_{pageCount}?module_name="+module_name, pageCurrent, pageSize, pageCount);
		model.addAttribute("pageHTML",pageHTML);
		model.addAttribute("activityGood",activityGood);
		
		
		return "good/goodManage";
	}
	
	
}
