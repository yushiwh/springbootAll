package com.jzt.cms.service;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.jzt.cms.model.ActivityModule;

@Mapper
public interface ActivityModuleService {

	@Select({
		"SELECT COUNT(*) FROM cms_activity_module"
	})
	int count(ActivityModule activityModule);

	@Select({
		"SELECT * FROM cms_activity_module",
		"limit #{start},#{end}",
	})
	List<ActivityModule> list(ActivityModule activityModule);

	@Select({
		"SELECT * FROM cms_activity_module WHERE id=#{id}"
	})
	ActivityModule selectOne(Integer id);
	
	@Update("UPDATE cms_activity_module SET module_name=#{module_name},module_type=#{module_type},module_subhead=#{module_subhead} WHERE id=#{id}")
	void update(ActivityModule activityModule);
	
	@Insert("INSERT INTO cms_activity_module (module_name,module_type,module_subhead) VALUES (#{module_name},#{module_type},#{module_subhead})")
	void insert(ActivityModule activityModule);
	
	@Delete("DELETE FROM cms_activity_module WHERE id=#{id}")
	void delete(Integer id);
}
