package com.zhuwenshen.mapper;

import com.zhuwenshen.model.TLoginHistory;

public interface TLoginHistoryMapperCustom {
	
	public TLoginHistory selectUsefulTokenByUserId(String userId);
}