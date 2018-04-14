package com.zhuwenshen.mapper;

import org.apache.ibatis.annotations.Param;

public interface MerchantGoodsLabelMapperCustom {
	
	public Integer deleteGoodsLabelNotInLabels(@Param("goodsId")String goodsId,	@Param("labels")String[] labels);

}
