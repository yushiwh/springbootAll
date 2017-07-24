package com.jzt.cms.model;

import java.util.Date;

public class ActivityPicture extends BaseObject{
	
    private Integer id;

    private String picture_name;

    private Integer picture_window;

    private String picture_modality;

    private Integer picture_type;

    private String picture_picture_interlinkage;

    private String create_user;

    private Date create_time;

    private String update_user;

    private Date update_time;
    
    private String image;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPicture_name() {
		return picture_name;
	}

	public void setPicture_name(String picture_name) {
		this.picture_name = picture_name;
	}

	public Integer getPicture_window() {
		return picture_window;
	}

	public void setPicture_window(Integer picture_window) {
		this.picture_window = picture_window;
	}

	public String getPicture_modality() {
		return picture_modality;
	}

	public void setPicture_modality(String picture_modality) {
		this.picture_modality = picture_modality;
	}

	public Integer getPicture_type() {
		return picture_type;
	}

	public void setPicture_type(Integer picture_type) {
		this.picture_type = picture_type;
	}

	public String getPicture_picture_interlinkage() {
		return picture_picture_interlinkage;
	}

	public void setPicture_picture_interlinkage(String picture_picture_interlinkage) {
		this.picture_picture_interlinkage = picture_picture_interlinkage;
	}

	public String getCreate_user() {
		return create_user;
	}

	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getUpdate_user() {
		return update_user;
	}

	public void setUpdate_user(String update_user) {
		this.update_user = update_user;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
    
    

}