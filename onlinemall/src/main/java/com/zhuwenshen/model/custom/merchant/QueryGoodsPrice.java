package com.zhuwenshen.model.custom.merchant;

import java.math.BigDecimal;
import java.util.List;

import com.zhuwenshen.model.custom.ClassDetail;

public class QueryGoodsPrice {

	private String id;
	private String goodsId;
	private BigDecimal price;
	private Integer num;
	private Integer allNum;
	private List<ClassDetail> detailList;
	
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}	
	public List<ClassDetail> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<ClassDetail> detailList) {
		this.detailList = detailList;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getAllNum() {
		return allNum;
	}
	public void setAllNum(Integer allNum) {
		this.allNum = allNum;
	}
}
