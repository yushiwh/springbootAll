package com.jzt.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.jzt.cms.model.ActivityCoupons;
import com.jzt.cms.model.GivingCoupons;
import com.jzt.cms.service.ActivityCouponsService;
import com.jzt.cms.util.Constant;
import com.jzt.cms.util.PageUtil;

@Controller
public class CouponsController {

	@Autowired
	private ActivityCouponsService activityCouponsService;
	
	/**
	 * 
	 *  @Description    : 优惠券列表(分页);
	 *  @Method_Name    : couponsList;
	 *  @param activityCoupons
	 *  @param module_name
	 *  @param pageCurrent
	 *  @param pageSize
	 *  @param pageCount
	 *  @param model
	 *  @return         :
	 *  @Creation Date  : 2017年7月21日 上午9:03:46 ;
	 *  @Author         : qiaowendong;
	 */
	@GetMapping("/admin/coupons_{pageCurrent}_{pageSize}_{pageCount}")
	public String couponsList(ActivityCoupons activityCoupons,String module_name,@PathVariable Integer pageCurrent,@PathVariable Integer pageSize,@PathVariable Integer pageCount,Model model){
		activityCoupons.setModule_name(module_name);
		//判断
		if(pageSize == 0){
			pageSize = 10;
		}
		if(pageCurrent == 0){
			pageCurrent = 1;
		}
		int rows = activityCouponsService.count(activityCoupons);
		if(pageCount == 0){
			pageCount = rows%pageSize == 0 ? (rows/pageSize) : (rows/pageSize) + 1;
		}
		
		//查询
		activityCoupons.setStart((pageCurrent - 1)*pageSize);
		activityCoupons.setEnd(pageSize);
		if(activityCoupons.getOrderBy()==null){
			activityCoupons.setOrderBy(Constant.OrderByAddDateDesc);
		}
		List<ActivityCoupons> couponsList = activityCouponsService.list(activityCoupons);
		
		//输出
		model.addAttribute("couponsList", couponsList);
		String pageHTML = PageUtil.getPageContent("coupons_{pageCurrent}_{pageSize}_{pageCount}？module_name="+module_name, pageCurrent, pageSize, pageCount);
		model.addAttribute("pageHTML",pageHTML);
		
		return "coupons/couponsManage";
	}
	
	@GetMapping("/amdin/addCoupons")
	public String savenCoupons(ActivityCoupons activityCoupons,Model model){
		
		
		
		
		return "coupons/addCoupons";
	}
	
	@PostMapping("/admin/addCoupons")
	public String addCoupons(ActivityCoupons activityCoupons,GivingCoupons givingCoupons){
		
		
		if(activityCoupons!=null){
			activityCouponsService.insert(activityCoupons);
		}
		
		
		
		
		return "";
	}
	
}
