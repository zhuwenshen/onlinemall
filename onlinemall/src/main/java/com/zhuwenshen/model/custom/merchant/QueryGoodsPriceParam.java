package com.zhuwenshen.model.custom.merchant;

import java.math.BigDecimal;

public class QueryGoodsPriceParam {

	private Integer pageSize = 10;
	private Integer pageNum = 1;
	private String goodsId;
	private String priceId;
	private String[] classDetailId;
	private BigDecimal price;
	private Integer num;
	
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String[] getClassDetailId() {
		return classDetailId;
	}
	public void setClassDetailId(String[] classDetailId) {
		this.classDetailId = classDetailId;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getPriceId() {
		return priceId;
	}
	public void setPriceId(String priceId) {
		this.priceId = priceId;
	}	
}
