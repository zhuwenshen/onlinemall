package com.zhuwenshen.model.custom.admin;

import java.math.BigDecimal;
import java.util.Date;

public class QueryAdminUserParam {
	
	private Integer pageNum = 1;
	private Integer pageSize = 10;
	
	
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
 

    /**
     * 用户类型；1游客 2普通用户 3商家 4商家服务员 5管理员 6超级管理员
     */
    private Integer userType;

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
    private Date unfreezingTimeStart;
    private Date unfreezingTimeEnd;
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
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
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
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
	public Date getUnfreezingTimeStart() {
		return unfreezingTimeStart;
	}
	public void setUnfreezingTimeStart(Date unfreezingTimeStart) {
		this.unfreezingTimeStart = unfreezingTimeStart;
	}
	public Date getUnfreezingTimeEnd() {
		return unfreezingTimeEnd;
	}
	public void setUnfreezingTimeEnd(Date unfreezingTimeEnd) {
		this.unfreezingTimeEnd = unfreezingTimeEnd;
	}
    
    

}
