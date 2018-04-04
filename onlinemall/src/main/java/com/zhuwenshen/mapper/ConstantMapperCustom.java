package com.zhuwenshen.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhuwenshen.model.custom.Constant;

public interface ConstantMapperCustom {
	
	public List<Constant> kindConstant(@Param("kind")String kind);

}
