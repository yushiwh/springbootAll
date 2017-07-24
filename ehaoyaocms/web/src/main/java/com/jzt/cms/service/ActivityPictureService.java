package com.jzt.cms.service;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.jzt.cms.model.ActivityPicture;

@Mapper
public interface ActivityPictureService {

	@Insert("INSERT INTO cms_activity_picture (picture_name,image,picture_type) values (#{picture_name},#{image},#{picture_type})")
	void insert(ActivityPicture activityPicture);
	
	@Insert("INSERT INTO cms_activity_picture (picture_name,image,picture_window,picture_picture_interlinkage,picture_type) values (#{picture_name},#{image},#{picture_window},#{picture_picture_interlinkage},#{picture_type})")
	void inserts(ActivityPicture activityPicture);
	
	@Select({
		"SELECT * FROM cms_activity_picture WHERE picture_name=#{picture_name} AND picture_type=#{picture_type}"
	})
	ActivityPicture selectOne(ActivityPicture activityPicture);

	@Update("UPDATE cms_activity_picture SET picture_name=#{picture_name},image=#{image} WHERE id=#{id} AND picture_type=#{picture_type}")
	void update(ActivityPicture activityPicture);

	@Delete("DELETE FROM cms_activity_picture WHERE picture_name=#{picture_name} AND picture_type=#{picture_type}")
	void delete(ActivityPicture activityPicture);
	
	@Select("SELECT * FROM cms_activity_picture")
	List<ActivityPicture> list(ActivityPicture activityPicture);

}
