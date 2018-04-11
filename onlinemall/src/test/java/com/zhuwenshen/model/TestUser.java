package com.zhuwenshen.model;

import com.zhuwenshen.annotation.PicUrl;

public class TestUser {

	@PicUrl
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
