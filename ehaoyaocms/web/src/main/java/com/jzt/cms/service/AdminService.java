package com.jzt.cms.service;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.jzt.cms.model.Admin;

@Mapper
public interface AdminService {

	@Select("SELECT * FROM `admin` where userName = #{userName} and password = #{password} and state = 1;")
	Admin findByNameAndPassword(Admin admin);

	@Insert("INSERT INTO `admin` (`id`, `userName`, `password`, `realName`, `age`, `phoneNumber`, `headPicture`, `addDate`, `updateDate`, `state`) VALUES (null, #{userName}, #{password}, #{realName}, #{age}, #{phoneNumber}, #{headPicture}, now(), now(), 1);")
	int insert(Admin admin);

}
