package com.zhuwenshen.model.custom;

import java.math.BigDecimal;
import java.util.Set;

import com.zhuwenshen.model.TUser;

public class User {

	private String id;
	
	private String phone;

	private String name;
	
	private String email;
	
	private String merchantId;
	
	private String merchantName;
	
	private Long merchantNum;
	
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
     * 用户类型；1游客 2普通用户 3商家 4商家服务员 5管理员 6超级管理员
     */
    private Integer userType;

	private Set<String> urls;

	
	
	public User() {
		super();
	}

	public User(TUser u) {
		id = u.getId();
		phone = u.getPhone();
		name = u.getName();
		email = u.getEmail();
		integral = u.getIntegral();
		allIntegral = u.getAllIntegral();
		fund = u.getFund();
		userType = u.getUserType();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Set<String> getUrls() {
		return urls;
	}

	public void setUrls(Set<String> urls) {
		this.urls = urls;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public Long getMerchantNum() {
		return merchantNum;
	}

	public void setMerchantNum(Long long1) {
		this.merchantNum = long1;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", phone=" + phone + ", name=" + name + ", email=" + email + ", merchantId="
				+ merchantId + ", merchantName=" + merchantName + ", merchantNum=" + merchantNum + ", integral="
				+ integral + ", allIntegral=" + allIntegral + ", fund=" + fund + ", userType=" + userType + ", urls="
				+ urls + "]";
	}
	
}
