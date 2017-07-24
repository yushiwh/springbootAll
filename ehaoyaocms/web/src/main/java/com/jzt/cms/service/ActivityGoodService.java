package com.jzt.cms.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.jzt.cms.model.ActivityGood;

@Mapper
public interface ActivityGoodService {

	@Select({
		"SELECT COUNT(*) FROM cms_activity_good WHERE module_name=#{module_name}"
	})
	int count(ActivityGood activityGood);

	@Select({
		"SELECT * FROM cms_activity_good WHERE module_name=#{module_name}",
		"limit #{start},#{end}"
	})
	List<ActivityGood> list(ActivityGood activityGood);

}
