package com.zhuwenshen.model.custom;

import java.math.BigDecimal;
import java.util.Set;

public class User {

	private String id;
	
	private String phone;

	private String name;

	private String passworld;
	
	private String email;
	
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

	public String getPassworld() {
		return passworld;
	}

	public void setPassworld(String passworld) {
		this.passworld = passworld;
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

	@Override
	public String toString() {
		return "User [id=" + id + ", phone=" + phone + ", name=" + name + ", passworld=" + passworld + ", email="
				+ email + ", integral=" + integral + ", allIntegral=" + allIntegral + ", fund=" + fund + ", userType="
				+ userType + ", urls=" + urls + "]";
	}
	
	
	
	
	
}
