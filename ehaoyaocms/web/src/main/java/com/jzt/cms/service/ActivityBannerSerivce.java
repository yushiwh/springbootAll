package com.jzt.cms.service;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.jzt.cms.model.ActivityBanner;

@Mapper
public interface ActivityBannerSerivce {

	@Select("SELECT COUNT(*) FROM cms_activity_banner WHERE module_name=#{module_name}")
	int count(ActivityBanner activityBanner);
	
	@Select({
		"SELECT * FROM cms_activity_banner",
		"WHERE module_name=#{module_name}",
		"limit #{start},#{end}"
		})
	List<ActivityBanner> list(ActivityBanner activityBanner);

	@Select("SELECT * FROM cms_activity_banner WHERE id=#{id}")
	ActivityBanner selectOne(Integer id);

	@Update("UPDATE cms_activity_banner SET banner_name=#{banner_name},picture_name=#{picture_name} WHERE id=#{id}")
	void update(ActivityBanner activityBanner);

	@Insert("INSERT INTO cms_activity_banner (banner_name,module_name,picture_name) VALUES (#{banner_name},#{module_name},#{picture_name})")
	void insert(ActivityBanner activityBanner);
	
	@Delete("DELETE FROM cms_activity_banner WHERE id=#{id}")
	void delete(Integer id);
	

	
}
