package com.zhuwenshen.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.zhuwenshen.model.TFrozenInformation;

public interface TFrozenInformationMapperCustom {

	public TFrozenInformation selectFrozenInforByUserId(@Param("userId")String userId, @Param("date")Date date);
}
