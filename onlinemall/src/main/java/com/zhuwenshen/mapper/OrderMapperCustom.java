package com.zhuwenshen.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhuwenshen.model.custom.merchant.QueryOrder;
import com.zhuwenshen.model.custom.merchant.QueryOrderInfor;
import com.zhuwenshen.model.custom.merchant.QueryOrderParam;


public interface OrderMapperCustom {

	public List<QueryOrder> selectOrder(QueryOrderParam param);

	public List<QueryOrderInfor> selectOrderInfo(@Param("orderInfoId")String orderInfoId,@Param("merchantId") String merchantId);
}
