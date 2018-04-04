package com.zhuwenshen.mapper;


import com.github.pagehelper.Page;
import com.zhuwenshen.model.custom.QueryApprovalMerchant;
import com.zhuwenshen.model.custom.QueryApprovalMerchantParam;

public interface ApprovalMerchantMapperCustom {
	
	public Page<QueryApprovalMerchant> selectApprovalMerchant(QueryApprovalMerchantParam qamp); 

}
