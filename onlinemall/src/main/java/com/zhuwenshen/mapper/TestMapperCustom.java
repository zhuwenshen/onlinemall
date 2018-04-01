package com.zhuwenshen.mapper;

import org.apache.ibatis.annotations.Param;

import com.zhuwenshen.model.Test;

public interface TestMapperCustom {
	
	public Test selectTTTT(@Param("name")String nam, @Param("passworld")String pas);
}