package com.jzt.cms.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jzt.cms.model.ActivityBanner;
import com.jzt.cms.model.ActivityPicture;
import com.jzt.cms.service.ActivityBannerSerivce;
import com.jzt.cms.service.ActivityPictureService;
import com.jzt.cms.util.Constant;
import com.jzt.cms.util.PageUtil;

@Controller
public class BannerController {

	@Autowired
	private ActivityBannerSerivce activityBannerService;
	
	@Autowired
	private ActivityPictureService activityPictureService;
	
	@GetMapping("/admin/banner_{pageCurrent}_{pageSize}_{pageCount}")
	public String bannerList(ActivityBanner activityBanner,ActivityPicture activityPicture,String module_name,@PathVariable Integer pageCurrent,@PathVariable Integer pageSize,@PathVariable Integer pageCount,Model model){
		activityBanner.setModule_name(module_name);
		//判断
		if(pageSize == 0){
			pageSize = 10;
		}
		if(pageCurrent == 0){
			pageCurrent = 1;
		}
		int rows = activityBannerService.count(activityBanner);
		if(pageCount == 0){
			pageCount = rows%pageSize == 0 ? (rows/pageSize) : (rows/pageSize) + 1;
		}
		
		//查询
		activityBanner.setStart((pageCurrent - 1)*pageSize);
		activityBanner.setEnd(pageSize);
		if(activityBanner.getOrderBy()==null){
			activityBanner.setOrderBy(Constant.OrderByAddDateDesc);
		}
		List<ActivityBanner> bannerList = activityBannerService.list(activityBanner);
		List<ActivityPicture> pictureList=activityPictureService.list(activityPicture);
		//输出
		model.addAttribute("module_name", module_name);
		model.addAttribute("pictureList", pictureList);
		model.addAttribute("bannerList", bannerList);
		String pageHTML = PageUtil.getPageContent("banner_{pageCurrent}_{pageSize}_{pageCount}?module_name="+module_name, pageCurrent, pageSize, pageCount);
		model.addAttribute("pageHTML",pageHTML);
		
		
		return "banner/bannerManage";
	}
	
	@GetMapping("/admin/saveBanner")
	public String saveBanner(ActivityBanner activityBanner,ActivityPicture activityPicture,Model model){
		if(activityBanner.getId()!=null){
			activityPicture.setPicture_type(1);
			activityBanner=activityBannerService.selectOne(activityBanner.getId());
			activityPicture=activityPictureService.selectOne(activityPicture);
		}
		model.addAttribute("activityBanner", activityBanner);
		model.addAttribute("activityPicture", activityPicture);
		return "banner/saveBanner";
	}
	
	@PostMapping("/admin/saveBanner")
	public String saveBanner(ActivityBanner activityBanner,ActivityPicture activityPicture,Model model,Integer pId,@RequestParam MultipartFile[] imageFile,HttpSession httpSession) throws Exception{
		
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
					activityBanner.setPicture_name(file.getOriginalFilename());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		
		if(activityBanner.getId()!=null){
			activityPicture.setId(pId);
			activityPicture.setPicture_type(1);
			activityPictureService.update(activityPicture);
			activityBannerService.update(activityBanner);
		}else{
			activityPicture.setPicture_type(1);
			activityPictureService.inserts(activityPicture);
			activityBannerService.insert(activityBanner);
		}
		String module_name=activityBanner.getModule_name();
		module_name=URLEncoder.encode(activityBanner.getModule_name(),"UTF-8");
		return "redirect:banner_0_0_0?module_name="+module_name;
	}
	
	@ResponseBody
	@DeleteMapping("/admin/deleteBanner")
	public String deleteBanner(Integer id){
		ActivityBanner activityBanner=activityBannerService.selectOne(id);
		ActivityPicture activityPicture=new ActivityPicture();
		activityPicture.setPicture_type(1);
		activityPicture.setPicture_name(activityBanner.getPicture_name());
		activityPictureService.delete(activityPicture);
		activityBannerService.delete(id);
		return "ok";
	}
}
