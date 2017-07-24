package com.jzt.cms.model;

import java.util.Date;

public class ActivityGood extends BaseObject{
    private Integer id;

    private Integer good_coding;

    private String good_name;

    private Integer good_type;

    private Long good_money;

    private Integer good_tag;

    private String good_brand;

    private String create_user;

    private Date create_time;

    private String update_user;

    private Date update_time;

    private String module_name;
    
    public String getModule_name() {
		return module_name;
	}

	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}

	public Integer getGood_coding() {
		return good_coding;
	}

	public void setGood_coding(Integer good_coding) {
		this.good_coding = good_coding;
	}

	public String getGood_name() {
		return good_name;
	}

	public void setGood_name(String good_name) {
		this.good_name = good_name;
	}

	public Integer getGood_type() {
		return good_type;
	}

	public void setGood_type(Integer good_type) {
		this.good_type = good_type;
	}

	public Long getGood_money() {
		return good_money;
	}

	public void setGood_money(Long good_money) {
		this.good_money = good_money;
	}

	public Integer getGood_tag() {
		return good_tag;
	}

	public void setGood_tag(Integer good_tag) {
		this.good_tag = good_tag;
	}

	public String getGood_brand() {
		return good_brand;
	}

	public void setGood_brand(String good_brand) {
		this.good_brand = good_brand;
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

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}