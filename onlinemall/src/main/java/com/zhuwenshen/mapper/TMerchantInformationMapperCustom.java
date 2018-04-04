package com.zhuwenshen.mapper;

import java.util.List;

import com.zhuwenshen.model.TMerchantInformation;
import com.zhuwenshen.model.custom.admin.QueryMerchantInformation;

public interface TMerchantInformationMapperCustom {
	
	public List<QueryMerchantInformation> selectMerchantInformation(TMerchantInformation mi);

}
