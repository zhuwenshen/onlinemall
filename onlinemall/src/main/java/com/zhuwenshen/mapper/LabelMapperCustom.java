package com.zhuwenshen.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhuwenshen.model.custom.QueryLabel;

public interface LabelMapperCustom {
	

	public List<QueryLabel> selectByPid(@Param("pid")String pid);
	
	public QueryLabel selectByPidAndName(@Param("pid")String pid, @Param("myName")String myName);
	
	public QueryLabel selectById(@Param("id")String id);
}
