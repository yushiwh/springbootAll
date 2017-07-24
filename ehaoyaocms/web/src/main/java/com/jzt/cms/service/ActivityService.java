package com.jzt.cms.service;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.jzt.cms.model.Activity;


@Mapper
public interface ActivityService {

	
	@Select({
		"<script>",
		"SELECT count(*) FROM `cms_activity`",
		"</script>"
	})
	int count(Activity activity);
	
	@Select({
		"<script>",
			"SELECT * FROM `cms_activity`",
			"<where>",
				"<when test='activity_coding!=null'>",
					"AND activity_coding LIKE CONCAT('%',#{activity_coding},'%')",
				"</when>",
				"<when test='activity_name!=null'>",
					"AND activity_name LIKE CONCAT('%',#{activity_name},'%')",
				"</when>",
				"<when test='activity_type!=0'>",
					"AND activity_type = #{activity_type}",
				"</when>",
				
				
				
				"<when test='activity_start_time!=null'>",
					"AND activity_start_time &gt; #{activity_start_time}", 
				"</when>",
				"<when test='activity_end_time!=null'>",
					"AND  and activity_end_time &lt; #{activity_end_time}",
				"</when>",
				"<when test='create_time!=null'>",
					"AND create_time &gt; #{create_time}",
				"</when>",
				"<when test='create_times!=null'>",
					"AND create_time &lt; #{create_times}",
				"</when>",



			"</where>",
			"limit #{start},#{end}",
		"</script>"
	})
	List<Activity> list(Activity activity);

	
	@Select({
		"<script>",
		"SELECT * FROM `cms_activity` where id=#{id}",
		"</script>"
	})
	Activity selectOne(Integer id);

	@Update("UPDATE cms_activity SET activity_name=#{activity_name},activity_coding=#{activity_coding},picture_name=#{picture_name},activity_background_image_way=#{activity_background_image_way} WHERE id=#{id}")
	void update(Activity activity);

	
	
	@Insert("INSERT INTO cms_activity (activity_name,activity_coding,picture_name,activity_background_image_way) values (#{activity_name},#{activity_coding},#{picture_name},#{activity_background_image_way})")
	void insert(Activity activity);

	@Delete("DELETE FROM cms_activity WHERE id=#{id}")
	void delete(Integer id);

}
