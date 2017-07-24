package com.jzt.cms.model;

import java.math.BigDecimal;
import java.util.Date;

public class ActivityCoupons extends BaseObject{
    private Integer id;

    private String coupons_number;

    private Integer coupons_type;

    private BigDecimal coupons_money;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCoupons_number() {
		return coupons_number;
	}

	public void setCoupons_number(String coupons_number) {
		this.coupons_number = coupons_number;
	}

	public Integer getCoupons_type() {
		return coupons_type;
	}

	public void setCoupons_type(Integer coupons_type) {
		this.coupons_type = coupons_type;
	}

	public BigDecimal getCoupons_money() {
		return coupons_money;
	}

	public void setCoupons_money(BigDecimal coupons_money) {
		this.coupons_money = coupons_money;
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