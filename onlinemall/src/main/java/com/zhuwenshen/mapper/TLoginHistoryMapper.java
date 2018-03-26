package com.zhuwenshen.mapper;

import org.apache.ibatis.annotations.Param;

import com.zhuwenshen.model.TLoginHistory;
import com.zhuwenshen.util.MyMapper;

public interface TLoginHistoryMapper extends MyMapper<TLoginHistory> {
	
	/**
	 * 通过有效的token查询
	 * @param token
	 * @return
	 */
	public TLoginHistory selectByUsefulToken(String token);
	
	/**
	 * 通过userid和客户端类型查询出当前
	 * @param userId
	 * @param clientType
	 * @return
	 */
	public TLoginHistory selectUsefulByUserIdAndClientType(@Param("userId")String userId, @Param("clientType")Integer clientType);
}