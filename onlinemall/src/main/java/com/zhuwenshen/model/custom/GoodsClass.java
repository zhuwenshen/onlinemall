package com.zhuwenshen.model.custom;

import java.util.List;

public class GoodsClass {
	private String id;
	private String goodsId;
	private Integer sort;
	private String name;
	private List<ClassDetail> details;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ClassDetail> getDetails() {
		return details;
	}

	public void setDetails(List<ClassDetail> details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "GoodsClass [id=" + id + ", goodsId=" + goodsId + ", sort=" + sort + ", name=" + name + ", details="
				+ details + "]";
	}
	
}
