package com.zhuwenshen.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhuwenshen.model.custom.admin.QueryMerchantInformation;

public interface MerchantImformatinMapperCustom {
	
	public List<QueryMerchantInformation> selectMerchantInformationById(@Param("merchantId") String merchantId);
	
	public void updateImgDes(QueryMerchantInformation qmi);
	


}
