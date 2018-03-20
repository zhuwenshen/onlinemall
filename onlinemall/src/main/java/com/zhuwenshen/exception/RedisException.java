package com.zhuwenshen.exception;

public class RedisException extends Exception {
	
	private static final long serialVersionUID = -7290891633566769443L;
	
	private String msg;
	
	public RedisException(String msg) {
		this.msg = msg; 
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
