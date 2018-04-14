package com.zhuwenshen.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhuwenshen.model.custom.merchant.QueryMerchantGoods;
import com.zhuwenshen.model.custom.merchant.QueryMerchantGoodsParam;

public interface MerchantGoodsMapperCustom {

	public List<QueryMerchantGoods> selectMerchantGoodsList(QueryMerchantGoodsParam qmgp);

	public List<?> selectChangedRecently(@Param("merchantId")String merchantId, @Param("date")Date date);

	/**
	 * 通过商家id和商品id获取标签
	 * @param merchantId
	 * @param object
	 * @return
	 */
	public List<?> selectGoodsLabel(@Param("merchantId")String merchantId, @Param("goodsId")String goodsId);

}
