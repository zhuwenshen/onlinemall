package com.zhuwenshen.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhuwenshen.mapper.TFrozenInformationMapperCustom;
import com.zhuwenshen.model.TFrozenInformation;

@Service
public class FrozenInformationService {
	
	@Autowired
	private TFrozenInformationMapperCustom fronzenMapperCustom;

	/**
	 * 判断用户是否被冻结，是：返回解冻日期，否返回null
	 * @param userId
	 * @return
	 */
	public Date isFrozen(String userId) {
		TFrozenInformation f = fronzenMapperCustom.selectFrozenInforByUserId(userId, new Date());
		if(f== null) {
			return null;
		}
		return f.getUnfreezingTime();
	}
	
	/**
	 * 判断用户是否被冻结
	 * @param userId
	 * @return
	 */
	public TFrozenInformation frozenInformation(String userId) {
		TFrozenInformation f = fronzenMapperCustom.selectFrozenInforByUserId(userId, new Date());
		
		return f;
	}
}
