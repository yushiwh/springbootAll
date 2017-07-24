package com.jzt.cms.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jzt.cms.model.Activity;
import com.jzt.cms.model.ActivityPicture;
import com.jzt.cms.service.ActivityPictureService;
import com.jzt.cms.service.ActivityService;
import com.jzt.cms.util.Constant;
import com.jzt.cms.util.PageUtil;


@Controller
public class ActivityController {
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private ActivityPictureService activityPictureService;
	
	
	/**
	 * 
	 *  @Description    : 活动列表(分页);
	 *  @Method_Name    : newsManage;
	 *  @param activity
	 *  @param pageCurrent
	 *  @param pageSize
	 *  @param pageCount
	 *  @param model
	 *  @return         :
	 *  @Creation Date  : 2017年7月19日 下午3:33:10 ;
	 *  @Author         : qiaowendong;
	 */
	@RequestMapping("/admin/activity_{pageCurrent}_{pageSize}_{pageCount}")
	public String activityList(Activity activity,@PathVariable Integer pageCurrent,@PathVariable Integer pageSize,@PathVariable Integer pageCount,Model model) {
		
		//判断
		if(pageSize == 0){
			pageSize = 10;
		}
		if(pageCurrent == 0){
			pageCurrent = 1;
		}
		int rows = activityService.count(activity);
		if(pageCount == 0){
			pageCount = rows%pageSize == 0 ? (rows/pageSize) : (rows/pageSize) + 1;
		}
		
		//查询
		activity.setStart((pageCurrent - 1)*pageSize);
		activity.setEnd(pageSize);
		if(activity.getOrderBy()==null){
			activity.setOrderBy(Constant.OrderByAddDateDesc);
		}
		List<Activity> activityList = activityService.list(activity);
		
		//输出
		model.addAttribute("activityList", activityList);
		String pageHTML = PageUtil.getPageContent("activity_{pageCurrent}_{pageSize}_{pageCount}?activity_name="+activity.getActivity_name()+"&activity_coding="+activity.getActivity_coding()+"&activity_type="+activity.getActivity_type(), pageCurrent, pageSize, pageCount);
		model.addAttribute("pageHTML",pageHTML);
		model.addAttribute("activity",activity);
		
		return "activity/activityManage";
	}
	
	
	/**
	 * 
	 *  @Description    : 新增/修改活动页面跳转;
	 *  @Method_Name    : addActivity;
	 *  @param activity
	 *  @param model
	 *  @return         :
	 *  @Creation Date  : 2017年7月19日 下午3:33:33 ;
	 *  @Author         : qiaowendong;
	 */
	@GetMapping("/admin/saveActivity")
	public String saveActivity(Activity activity,Model model){
		ActivityPicture activityPicture=new ActivityPicture();
		if(activity.getId() != null){
			activity=activityService.selectOne(activity.getId());
			activityPicture.setPicture_type(0);
			activityPicture.setPicture_name(activity.getPicture_name());
			activityPicture=activityPictureService.selectOne(activityPicture);
		}
		model.addAttribute("activityPicture", activityPicture);
		model.addAttribute("activity", activity);
		return "activity/saveActivity";
	}
	
	/**
	 * 
	 *  @Description    : 新增/修改单个活动;
	 *  @Method_Name    : saveActivity;
	 *  @param activity
	 *  @param pId
	 *  @param activityPicture
	 *  @param model
	 *  @param imageFile
	 *  @param httpSession
	 *  @return         :
	 *  @Creation Date  : 2017年7月20日 上午11:46:09 ;
	 *  @Author         : qiaowendong;
	 */
	@PostMapping("/admin/saveActivity")
	public String saveActivity(Activity activity,Integer pId,ActivityPicture activityPicture,Model model,@RequestParam MultipartFile[] imageFile,HttpSession httpSession){
		
		
		for (MultipartFile file : imageFile) {
			if (file.isEmpty()) {
				System.out.println("文件未上传");
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				Random random = new Random();
				Date date = new java.util.Date();
				String strDate = sdf.format(date);
				String fileName = strDate + "_" + random.nextInt(1000) + file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."),file.getOriginalFilename().length());
				String realPath = httpSession.getServletContext().getRealPath("/userfiles");
				System.out.println("realPath : "+realPath);
				
				activityPicture.setPicture_name(file.getOriginalFilename());
				activityPicture.setImage("/userfiles/"+fileName);
				
				//System.out.println("图片名称？"+file.getOriginalFilename());
				
				//String[] pictureNmae=file.getOriginalFilename().split(".");
				
				try {
					FileUtils.copyInputStreamToFile(file.getInputStream(),new File(realPath, fileName));
					activity.setPicture_name(file.getOriginalFilename());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if(activity.getId()!=null){
			activityPicture.setId(pId);
			activityPicture.setPicture_type(0);
			activityPictureService.update(activityPicture);
			activityService.update(activity);
		} else {
			activityPicture.setPicture_type(0);
			activityPictureService.insert(activityPicture);
			activityService.insert(activity);
		}
		
		return "redirect:activity_0_0_0";
	}
	
	/**
	 * 
	 *  @Description    :删除单个活动;
	 *  @Method_Name    : deleteActivity;
	 *  @param activity
	 *  @param id
	 *  @return         :
	 *  @Creation Date  : 2017年7月20日 上午11:46:30 ;
	 *  @Author         : qiaowendong;
	 */
	@ResponseBody
	@DeleteMapping("/admin/deleteActivity")
	public String deleteActivity(Activity activity,Integer id){
		activity=activityService.selectOne(id);
		ActivityPicture activityPicture=new ActivityPicture();
		activityPicture.setPicture_type(0);
		activityPicture.setPicture_name(activity.getPicture_name());
		activityPictureService.delete(activityPicture);
		activityService.delete(id);
		return "ok";
	}
	
	
	
	
	
	
}
