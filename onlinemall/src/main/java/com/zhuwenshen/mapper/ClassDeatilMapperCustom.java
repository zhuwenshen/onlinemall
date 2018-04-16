package com.zhuwenshen.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhuwenshen.model.custom.GoodsClass;

public interface ClassDeatilMapperCustom {

	public void deleteUnuseClassDetail(@Param("classId")String classId, @Param("detailNameList")List<String> detailNameList);

	public void deleteUnuserGoodsClass(@Param("goodsId")String goodsId, @Param("classNameList")List<String> classNameList);
	
	//查询一个商品的有效的标签
	public List<GoodsClass> selectGoodsClassByGoodsId(@Param("goodsId")String goodsId, @Param("className")String className, @Param("detailName")String detailName);

}
