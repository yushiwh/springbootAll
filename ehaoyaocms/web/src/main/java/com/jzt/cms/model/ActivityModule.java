package com.jzt.cms.model;

import java.util.Date;

public class ActivityModule extends BaseObject{
    private Integer id;

    private String module_name;

    private Integer module_type;

    private String module_subhead;

    private String create_user;

    private Date create_time;

    private String update_user;

    private Date update_time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModule_name() {
		return module_name;
	}

	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}

	public Integer getModule_type() {
		return module_type;
	}

	public void setModule_type(Integer module_type) {
		this.module_type = module_type;
	}

	public String getModule_subhead() {
		return module_subhead;
	}

	public void setModule_subhead(String module_subhead) {
		this.module_subhead = module_subhead;
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

}