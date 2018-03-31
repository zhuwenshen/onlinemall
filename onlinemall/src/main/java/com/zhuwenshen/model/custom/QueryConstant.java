package com.zhuwenshen.model.custom;

public class QueryConstant {
	
	private String kind;
	private String kindName;
	private String name;
	private String nameCn;
	private String value1;
	private Boolean useful;
	private Integer pageNum;
	private Integer pageSize = 10;
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getKindName() {
		return kindName;
	}
	public void setKindName(String kindName) {
		this.kindName = kindName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameCn() {
		return nameCn;
	}
	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}
	public String getValue1() {
		return value1;
	}
	public void setValue1(String value1) {
		this.value1 = value1;
	}
	public Boolean getUseful() {
		return useful;
	}
	public void setUseful(Boolean useful) {
		this.useful = useful;
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
	@Override
	public String toString() {
		return "QueryConstant [kind=" + kind + ", kindName=" + kindName + ", name=" + name + ", nameCn=" + nameCn
				+ ", value1=" + value1 + ", useful=" + useful + ", pageNum=" + pageNum + ", pageSize=" + pageSize + "]";
	}
	
}
