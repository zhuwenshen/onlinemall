package com.zhuwenshen.model.custom.merchant;

public class QueryMerchantLabel {
	
	private String id;
	
	private String name;
	
	private String allName;

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

	public String getAllName() {
		return allName;
	}

	public void setAllName(String allName) {
		this.allName = allName;
	}

	@Override
	public String toString() {
		return "QueryMerchantLabel [id=" + id + ", name=" + name + "]";
	}

}
