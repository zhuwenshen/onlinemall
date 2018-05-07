package com.zhuwenshen.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BriefGoodsMapperCustom {

	public void deleteAll();
	public void insertAll();
	
	/**
	 * 查询订单统计数据
	 * @param priceIdList  价格id数组
	 * @param type 类型 （1：所有订单，2：成功订单数 ， 3： 失败订单数）
	 * @return
	 */
	public Integer selectOrderNum(@Param("priceIdList")List<String> priceIdList, @Param("type")Integer type); 
	
	/**
	 * 查询商品统计数量
	 * @param priceIdList  价格id数组
	 * @param type 类型 （1：所有订单，2：成功订单数 ， 3： 失败订单数）
	 * @return
	 */
	public Integer selectGoodsNum(@Param("priceIdList")List<String> priceIdList, @Param("type")Integer type); 
	
	/**
	 * 查询商品月售数量
	 * @param priceIdList 价格id数组
	 * @param type （1：所有订单，2：成功订单数 ， 3： 失败订单数）
	 * @param today 今天日期
	 * @param lastMonDate 30天前日期
	 * @return
	 */
	public Integer selectMonSoldNum(@Param("priceIdList")List<String> priceIdList, @Param("today")Date today, @Param("lastMonDate")Date lastMonDate); 


	/**
	 * 平均评分
	 * @param priceIdList 价格id数组
	 * @return
	 */
	public Double selectAvgScore(@Param("priceIdList")List<String> priceIdList); 
	
	public String selectGoodsLabel(@Param("goodsId")String goodsId);
}
