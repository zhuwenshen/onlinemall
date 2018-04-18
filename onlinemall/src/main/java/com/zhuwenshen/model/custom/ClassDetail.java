package com.zhuwenshen.model.custom;

public class ClassDetail {
	private String id;
	private String classId;
	private Integer sort;
	private String name;
	private String className;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
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

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public String toString() {
		return "ClassDetail [id=" + id + ", classId=" + classId + ", sort=" + sort + ", name=" + name + ", className="
				+ className + "]";
	}

	
	
}
