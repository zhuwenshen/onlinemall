package com.zhuwenshen.model.custom.merchant;

import java.util.Date;

public class QueryMerchantWaiterParam {
	
	private Integer pageNum = 1;
	private Integer pageSize = 10;

	private String id; 	

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
