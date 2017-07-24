package com.jzt.cms.model;

public class GivingCoupons {
	
	private	String	couponNum; //优惠券金额或折扣率文本:5
	
	private	Integer	couponId; //优惠券id(优惠券编号)
	
	private	String	couponUnit;//单位:元或折
	
	private	String	couponTime;//使用期限(9月1日-9月30日)

	private	Integer	couponType;//优惠券类型 (1商城券，2全球购，3通用券，4店铺券)
	
	private	String	couponTypeString;//优惠券类型名称:(商城券，全球购，通用券，店铺券)
	
	private	Integer	type;//优惠券状态类型(1未使用   2已使用  3已过期   4未领取)
	
	private	Integer	isGet; //领取(1已领取   2未领取   3不可领取)

	private	String	couponDesc;//优惠券描述：保健品区域可用
	
	private	String	couponName;//优惠券名称：新用户药品保健代金券
	
	private	String	couponRemark;//可用优惠券描述:最高抵200元

	public String getCouponNum() {
		return couponNum;
	}

	public void setCouponNum(String couponNum) {
		this.couponNum = couponNum;
	}

	public Integer getCouponId() {
		return couponId;
	}

	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}

	public String getCouponUnit() {
		return couponUnit;
	}

	public void setCouponUnit(String couponUnit) {
		this.couponUnit = couponUnit;
	}

	public String getCouponTime() {
		return couponTime;
	}

	public void setCouponTime(String couponTime) {
		this.couponTime = couponTime;
	}

	public Integer getCouponType() {
		return couponType;
	}

	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}

	public String getCouponTypeString() {
		return couponTypeString;
	}

	public void setCouponTypeString(String couponTypeString) {
		this.couponTypeString = couponTypeString;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getIsGet() {
		return isGet;
	}

	public void setIsGet(Integer isGet) {
		this.isGet = isGet;
	}

	public String getCouponDesc() {
		return couponDesc;
	}

	public void setCouponDesc(String couponDesc) {
		this.couponDesc = couponDesc;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getCouponRemark() {
		return couponRemark;
	}

	public void setCouponRemark(String couponRemark) {
		this.couponRemark = couponRemark;
	}

}
