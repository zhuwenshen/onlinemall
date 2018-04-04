package com.zhuwenshen.model.custom;

import com.zhuwenshen.model.TGlobalConstant;

public class Constant {

	private String kind;
	private String name;
	private String nameCn;
	private String value;
	
	
	public Constant() {
		super();
	}
	
	
	public Constant(String kind, String name) {
		super();
		this.kind = kind;
		this.name = name;
	}


	public Constant(String kind, String name, String value) {
		super();
		this.kind = kind;
		this.name = name;
		this.value = value;
	}


	public Constant(TGlobalConstant tGlobalConstant) {
		this.kind = tGlobalConstant.getKind();
		this.name = tGlobalConstant.getName();
		this.value = tGlobalConstant.getValue1();
		this.nameCn = tGlobalConstant.getNameCn();
	}


	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getNameCn() {
		return nameCn;
	}
	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}
	
	@Override
	public String toString() {
		return "Constant [kind=" + kind + ", name=" + name + ", nameCn=" + nameCn + ", value=" + value + "]";
	}
	
	
	
	
}
