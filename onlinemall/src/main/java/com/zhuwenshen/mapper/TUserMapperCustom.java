package com.zhuwenshen.mapper;

public interface TUserMapperCustom {
	public int selectCountPhoneOrLoginId(String phone);
	
	public int selectCountById(String id);
}