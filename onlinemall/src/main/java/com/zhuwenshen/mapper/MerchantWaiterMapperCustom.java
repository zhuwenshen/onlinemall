package com.zhuwenshen.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhuwenshen.model.custom.merchant.QueryMerchantWaiter;
import com.zhuwenshen.model.custom.merchant.QueryMerchantWaiterParam;

public interface MerchantWaiterMapperCustom {
	
	public List<QueryMerchantWaiter> selectMerchantWaiter(QueryMerchantWaiterParam mwp);
	
	public List<QueryMerchantWaiter> selectChangedrecently(@Param("merchantId")String merchantId);

}
