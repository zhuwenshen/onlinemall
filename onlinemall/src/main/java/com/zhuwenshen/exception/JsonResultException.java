package com.zhuwenshen.exception;

import com.zhuwenshen.model.custom.JsonResult;

public class JsonResultException extends Exception {

	private static final long serialVersionUID = 159227080159661295L;
	private JsonResult json;

	public JsonResultException(JsonResult json) {
		super();
		this.json = json;
	}

	public JsonResult getJson() {
		return json;
	}

	public void setJson(JsonResult json) {
		this.json = json;
	}
	
	
}
