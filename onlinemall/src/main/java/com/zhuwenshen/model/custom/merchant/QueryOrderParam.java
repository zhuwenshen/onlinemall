package com.zhuwenshen.model.custom.merchant;

import java.util.Date;

public class QueryOrderParam {
	
	private Integer pageNum = 1;
	private Integer pageSize = 10;

	private String id;
	 /**
     * 用户id
     */
    private String userId;
    
    /**
     * 商家id
     */
    private String merchantId;
    
    /**
     * 订单流程状态
     */
    private Integer orderStatus;
    
    /**
     * 订单流程状态查询 ： 1未支付 5未完成 14已完成
     */
    private Integer queryOrderStatus;
    
    private Date createTimeStart;
    private Date createTimeEnd;
    
    /**
     * 最近一个月的时间
     */
    private Date createTimeMon;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Integer getQueryOrderStatus() {
		return queryOrderStatus;
	}
	public void setQueryOrderStatus(Integer queryOrderStatus) {
		this.queryOrderStatus = queryOrderStatus;
	}
	public Date getCreateTimeStart() {
		return createTimeStart;
	}
	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}
	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}
	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}
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
	public Date getCreateTimeMon() {
		return createTimeMon;
	}
	public void setCreateTimeMon(Date createTimeMon) {
		this.createTimeMon = createTimeMon;
	} 
	
}
