package com.zhuwenshen.model.custom;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.Page;
import com.zhuwenshen.aop.ConstantTranslateFilter;

public class JsonResult {
	
	private static ConstantTranslateFilter ctFilter = new ConstantTranslateFilter();

	private boolean status;
	
	private String msg;
	
	private Object data;
	
	
	public JsonResult() {
		super();
	}

	public JsonResult(boolean b) {
		status = true;
	}	

	public JsonResult(boolean status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}	

	public JsonResult(boolean status, String msg, Object data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public static JsonResult ok() {
		return new JsonResult(true);
	}
	
	public static JsonResult ok(String msg) {
		return new JsonResult(true, msg);
	}
	
	public static JsonResult ok(String msg, Object o) {
		return new JsonResult(true, msg, o);
	}
	
	public static JsonResult fail(String msg) {
		return new JsonResult(false, msg);
	}
	
	
	/**
	 * 通过fastjson装换
	 * @param o
	 * @return
	 */
	private static String tlToSting(Object o) {
		return JSON.toJSONString(o,ctFilter,  SerializerFeature.WriteMapNullValue);
	}
	
	public static String okToPageList(Page<?> page) {
		StringBuilder json = new StringBuilder();
		if(page != null) {
			json.append("{")
			.append("\"status\":true")
			.append(",\"msg\":\"\"")
			.append(",\"data\":");
			
			json.append("{")
				.append("\"pageNum\":"+page.getPageNum())
				.append(",\"pageSize\":"+page.getPageSize())
				.append(",\"startRow\":"+page.getStartRow())
				.append(",\"endRow\":"+page.getEndRow())
				.append(",\"total\":"+page.getTotal())
				.append(",\"pages\":"+page.getPages())
				.append(",\"pageSizeZero\":"+page.getPageSizeZero())
				.append(",\"list\":"+tlToSting(page.getResult()))
//				.append(",\"list\":"+JsonUtils.objectToJson(page.getResult()))
				.append("}");
			
			
			json.append("}");	
		}
		
		
		return json.toString();
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
