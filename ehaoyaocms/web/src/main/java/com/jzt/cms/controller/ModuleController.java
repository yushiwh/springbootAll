package com.jzt.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzt.cms.model.ActivityModule;
import com.jzt.cms.service.ActivityModuleService;
import com.jzt.cms.util.Constant;
import com.jzt.cms.util.PageUtil;

@Controller
public class ModuleController {

	@Autowired
	private ActivityModuleService activityModuleService;
	
	@GetMapping("/admin/module_{pageCurrent}_{pageSize}_{pageCount}")
	public String moduleList(ActivityModule activityModule,@PathVariable Integer pageCurrent,@PathVariable Integer pageSize,@PathVariable Integer pageCount,Model model){
		//判断
		if(pageSize == 0){
			pageSize = 10;
		}
		if(pageCurrent == 0){
			pageCurrent = 1;
		}
		int rows = activityModuleService.count(activityModule);
		if(pageCount == 0){
			pageCount = rows%pageSize == 0 ? (rows/pageSize) : (rows/pageSize) + 1;
		}
		
		//查询
		activityModule.setStart((pageCurrent - 1)*pageSize);
		activityModule.setEnd(pageSize);
		if(activityModule.getOrderBy()==null){
			activityModule.setOrderBy(Constant.OrderByAddDateDesc);
		}
		List<ActivityModule> moduleList = activityModuleService.list(activityModule);
		
		//输出
		model.addAttribute("moduleList", moduleList);
		String pageHTML = PageUtil.getPageContent("banner_{pageCurrent}_{pageSize}_{pageCount}", pageCurrent, pageSize, pageCount);
		model.addAttribute("pageHTML",pageHTML);
		model.addAttribute("activityModule",activityModule);
		
		return "module/moduleManage";
	}
	
	@GetMapping("/admin/saveModule")
	public String saveModule(ActivityModule activityModeule,Model model){
		if(activityModeule.getId()!=null){
			activityModeule=activityModuleService.selectOne(activityModeule.getId());
		}
		model.addAttribute("activityModeule", activityModeule);
		return "module/saveModule";
	}
	
	@PostMapping("/admin/saveModule")
	public String saceModule(ActivityModule activityModule){
		
		if(activityModule.getId()!=null){
			activityModuleService.update(activityModule);
		}else{
			activityModuleService.insert(activityModule);
		}
		
		return "redirect:module_0_0_0";
	}
	
	@ResponseBody
	@DeleteMapping("/admin/deleteModule")
	public String deleteModule(Integer id){
		activityModuleService.delete(id);
		return "ok";
	}

}
