package com.zhuwenshen.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.zhuwenshen.model.custom.merchant.QueryGoodsPrice;

public interface GoodsPriceMapperCustom {

	public List<String> selectPriceByGoodsAndClass(
			@Param("goodsId")String goodsId, @Param("classDetailIdList")String[] classDetailIdList, @Param("len") int len);

	/**
	 * 根据priceId查找出详细信息
	 * @param string
	 * @return
	 */
	public QueryGoodsPrice selectOneGoodsPrice(@Param("priceId")String priceId);

	/**
	 * 分页查询商品价格信息
	 * @param goodsId
	 * @param classDetailId
	 * @param len
	 * @return
	 */
	public Page<?> selectPriceByGoodsAndClassForPage(
			@Param("goodsId")String goodsId, @Param("classDetailIdList")String[] classDetailIdList, @Param("len") int len);
	
	public List<?> selectGoodsPrice(@Param("priceIdList")List<String> priceIdList);	
}
