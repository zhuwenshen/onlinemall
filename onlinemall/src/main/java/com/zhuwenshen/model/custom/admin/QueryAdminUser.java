package com.zhuwenshen.model.custom.admin;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuwenshen.annotation.ConstantTranslate;

public class QueryAdminUser {
	
	private String id;

    /**
     * 用户昵称
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 身份证号
     */
    private String idNumber;

    /**
     * 头像
     */
    private String headPortraitUrl;

    /**
     * 积分,单位分
     */
    private BigDecimal integral;

    /**
     * 总积分
     */
    private BigDecimal allIntegral;

    /**
     * 资金
     */
    private BigDecimal fund;

    /**
     * 1男 2女 3隐藏
     */
    private Integer sex;
    @ConstantTranslate(kind="sex",valueMethod="getSex")
    private String sexTL;

    /**
     * 出生日期 格式YYYY-MM-DD
     */
    @JSONField(format="yyyy-MM-dd")
    private Date birthdate;

    /**
     * 用户类型；1游客 2普通用户 3商家 4商家服务员 5管理员 6超级管理员
     */
    private Integer userType;
    @ConstantTranslate(kind="user_type",valueMethod="getUserType")
    private String userTypeTL;

    /**
     * 登录账号；用于登录账号，默认手机号，不可以更改
     */
    private String loginId;

    /**
     * 是否冻结；0否 1是 
     */
    private Boolean frozen;

    /**
     * 解封时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date unfreezingTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getHeadPortraitUrl() {
		return headPortraitUrl;
	}

	public void setHeadPortraitUrl(String headPortraitUrl) {
		this.headPortraitUrl = headPortraitUrl;
	}

	public BigDecimal getIntegral() {
		return integral;
	}

	public void setIntegral(BigDecimal integral) {
		this.integral = integral;
	}

	public BigDecimal getAllIntegral() {
		return allIntegral;
	}

	public void setAllIntegral(BigDecimal allIntegral) {
		this.allIntegral = allIntegral;
	}

	public BigDecimal getFund() {
		return fund;
	}

	public void setFund(BigDecimal fund) {
		this.fund = fund;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getUserTypeTL() {
		return userTypeTL;
	}

	public void setUserTypeTL(String userTypeTL) {
		this.userTypeTL = userTypeTL;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public Boolean getFrozen() {
		return frozen;
	}

	public void setFrozen(Boolean frozen) {
		this.frozen = frozen;
	}

	public Date getUnfreezingTime() {
		return unfreezingTime;
	}

	public void setUnfreezingTime(Date unfreezingTime) {
		this.unfreezingTime = unfreezingTime;
	}

	public String getSexTL() {
		return sexTL;
	}

	public void setSexTL(String sexTL) {
		this.sexTL = sexTL;
	}
	
}
