package com.zhuwenshen.mapper;

import org.apache.ibatis.annotations.Param;

import com.zhuwenshen.model.TLoginHistory;

public interface TLoginHistoryMapperCustom  {

	//public TLoginHistory selectByUsefulToken(String token);

	public TLoginHistory selectByUsefulTokenAndIp(@Param("token")String token, @Param("ip")String ip);

	
}