package com.zhuwenshen.model.custom;

import com.zhuwenshen.util.JsonUtils;

public class RedisKey {
	
	private Class<?> clazz;
	
	//常量表为system
	private String token;
	
	//过期时间
	private Integer expire;
	
	private RedisKind kind;

	
	
	public RedisKey() {
		super();
	}

	public RedisKey(Class<?> clazz, String token, Integer expire, RedisKind kind) {
		super();
		this.clazz = clazz;
		this.token = token;
		this.expire = expire;
		this.kind = kind;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getExpire() {
		return expire;
	}

	public void setExpire(Integer expire) {
		this.expire = expire;
	}

	public RedisKind getKind() {
		return kind;
	}

	public void setKind(RedisKind kind) {
		this.kind = kind;
	}

	public static RedisKey key(Class<?> clazz, String token) {
		return new RedisKey(clazz, token, 0, null);
	}
	
	public static RedisKey key(Class<?> clazz, String token , int time) {
		return new RedisKey(clazz, token, time, null);
	}
	
	public static RedisKey key(Class<?> clazz, String token , int time, RedisKind kind) {
		return new RedisKey(clazz, token, time, kind);
	}
	
	
	public static RedisKey key(Class<?> clazz, String token, RedisKind kind) {
		return new RedisKey(clazz, token, 0, kind);
	}

	@Override
	public String toString() {
		return JsonUtils.objectToJson(this);
	}
	
	

}
