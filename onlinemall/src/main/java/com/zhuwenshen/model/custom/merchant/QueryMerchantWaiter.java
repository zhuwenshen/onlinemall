package com.zhuwenshen.model.custom.merchant;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class QueryMerchantWaiter {

	private String id; 	
    /**
     * 真实姓名
     */
    private String name;   
  

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
    
    
}
