package com.jzt.cms.service;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.jzt.cms.model.ActivityCoupons;

@Mapper
public interface ActivityCouponsService {

	@Select("SELECT COUNT(*) FROM cms_activity_coupons WHERE module_name=#{module_name}")
	int count(ActivityCoupons activityCoupons);
	
	@Select({
		"SELECT * FROM cms_activity_coupons WHERE module_name=#{module_name}",
		"limit #{start},#{end}"
	})
	List<ActivityCoupons> list(ActivityCoupons activityCoupons);

	@Insert("INSERT INTO cms_activity_coupons ")
	void insert(ActivityCoupons activityCoupons);

	
	

	

}
