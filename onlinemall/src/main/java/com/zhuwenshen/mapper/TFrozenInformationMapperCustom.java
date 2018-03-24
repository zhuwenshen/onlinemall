package com.zhuwenshen.mapper;

import java.util.Date;

import com.zhuwenshen.model.TFrozenInformation;

public interface TFrozenInformationMapperCustom {

	public TFrozenInformation selectFrozenInforByUserId(String userId,Date date);
}
