package com.zhuwenshen.mapper;

import org.apache.ibatis.annotations.Param;

import com.zhuwenshen.model.custom.UpdateConstant;

public interface TGlobalConstantMapperCustom {
	
	public UpdateConstant getConstantById(@Param("id")String id);

}
