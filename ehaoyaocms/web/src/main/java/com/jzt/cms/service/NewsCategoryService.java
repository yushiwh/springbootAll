package  com.jzt.cms.service;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.jzt.cms.model.NewsCategory;

@Mapper
public interface NewsCategoryService {
	
	@Select("SELECT * FROM `NEWS_CATEGORY` where id = #{id};")
	NewsCategory findById(NewsCategory newsCategory);
	
	@Select({
		"<script>",
		"SELECT * FROM `NEWS_CATEGORY`",
		"WHERE state = 1",
			"<when test='name!=null'>",
				"AND name LIKE CONCAT('%',#{name},'%')",
			"</when>",
			"order by addDate desc limit #{start},#{end}",
		"</script>"
	})
	List<NewsCategory> list(NewsCategory newsCategory);
	
	@Select({
		"<script>",
		"SELECT count(*) FROM `NEWS_CATEGORY`",
		"WHERE state = 1",
			"<when test='name!=null'>",
				"AND name LIKE CONCAT('%',#{name},'%')",
			"</when>",
		"</script>"
	})
	int count(NewsCategory newsCategory);
	
	@Insert("INSERT INTO `NEWS_CATEGORY` (`id`, `name`, `description`, `image`, `addDate`, `state`) VALUES (null, #{name}, #{description}, #{image}, now(), 1);")
	int insert(NewsCategory newsCategory);
	
	@Update("UPDATE `NEWS_CATEGORY` SET `name` = #{name}, `description` = #{description}, `image` = #{image} WHERE `id` = #{id};")
	int update(NewsCategory newsCategory);
	
	@Update("UPDATE `NEWS_CATEGORY`SET `state` = #{state} WHERE `id` = #{id};")
	int updateState(NewsCategory newsCategory);
}
