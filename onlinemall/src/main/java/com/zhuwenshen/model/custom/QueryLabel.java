package com.zhuwenshen.model.custom;

import java.util.List;

public class QueryLabel {

	 private String id;

    private String name;

    /**
     * 所有标签的名称（包括自身，空格隔开）
     */
    private String allName;

    private String pId;
    
    private List<QueryLabel> children;
    
    private QueryLabel parent;
    
    private QueryLabel child;

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

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public List<QueryLabel> getChildren() {
		return children;
	}

	public void setChildren(List<QueryLabel> children) {
		this.children = children;
	}

	public QueryLabel getParent() {
		return parent;
	}

	public void setParent(QueryLabel parent) {
		this.parent = parent;
	}

	public QueryLabel getChild() {
		return child;
	}

	public void setChild(QueryLabel child) {
		this.child = child;
	}
    
}
