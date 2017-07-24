package com.jzt.cms.model;

import java.util.Date;

public class Activity  extends BaseObject{
	
    private Integer id;

    private String activity_name;
    
    private String activity_coding;

    private int activity_type;
    
    private Date activity_start_time;

	private Date activity_end_time;

    private String picture_name;

    private Integer activity_background_image_way;

    private String create_user;

    private Date create_time;

    private String update_user;

    private Date update_time;
    
    private Date create_times;
    
    private Integer activity_state;

	public Date getCreate_times() {
		return create_times;
	}

	public void setCreate_times(Date create_times) {
		this.create_times = create_times;
	}

	public int getActivity_type() {
		return activity_type;
	}

	public void setActivity_type(int activity_type) {
		this.activity_type = activity_type;
	}

	public String getActivity_coding() {
		return activity_coding;
	}

	public void setActivity_coding(String activity_coding) {
		this.activity_coding = activity_coding;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getActivity_name() {
		return activity_name;
	}

	public void setActivity_name(String activity_name) {
		this.activity_name = activity_name;
	}

	public Date getActivity_start_time() {
		return activity_start_time;
	}

	public void setActivity_start_time(Date activity_start_time) {
		this.activity_start_time = activity_start_time;
	}

	public Date getActivity_end_time() {
		return activity_end_time;
	}

	public void setActivity_end_time(Date activity_end_time) {
		this.activity_end_time = activity_end_time;
	}

	public String getPicture_name() {
		return picture_name;
	}

	public void setPicture_name(String picture_name) {
		this.picture_name = picture_name;
	}

	public Integer getActivity_background_image_way() {
		return activity_background_image_way;
	}

	public void setActivity_background_image_way(Integer activity_background_image_way) {
		this.activity_background_image_way = activity_background_image_way;
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

	public Integer getActivity_state() {
		return activity_state;
	}

	public void setActivity_state(Integer activity_state) {
		this.activity_state = activity_state;
	}
    
    
}