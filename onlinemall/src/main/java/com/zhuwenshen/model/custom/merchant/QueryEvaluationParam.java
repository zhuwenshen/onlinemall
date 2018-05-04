package com.zhuwenshen.model.custom.merchant;

import java.util.Date;

public class QueryEvaluationParam {
	
	private Integer pageNum = 1;
	private Integer pageSize = 10;
	
	
    private String id;   

    /**
     * 评价时间
     */
    private Date userTimeStart;
    private Date userTimeEnd;       
  
    private String orderInforId;        
    
    /**
     * 订单id
     */
    private String orderId;
    
    private String merchantId;

    /**
     * 商品种类id
     */
    private String priceId;

    private String goodsId;
    private String goodsName;
   
    private Date monthStartTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getUserTimeStart() {
		return userTimeStart;
	}

	public void setUserTimeStart(Date userTimeStart) {
		this.userTimeStart = userTimeStart;
	}

	public Date getUserTimeEnd() {
		return userTimeEnd;
	}

	public void setUserTimeEnd(Date userTimeEnd) {
		this.userTimeEnd = userTimeEnd;
	}

	public String getOrderInforId() {
		return orderInforId;
	}

	public void setOrderInforId(String orderInforId) {
		this.orderInforId = orderInforId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getPriceId() {
		return priceId;
	}

	public void setPriceId(String priceId) {
		this.priceId = priceId;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Date getMonthStartTime() {
		return monthStartTime;
	}

	public void setMonthStartTime(Date monthStartTime) {
		this.monthStartTime = monthStartTime;
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
	     
}