package com.zhuwenshen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.zhuwenshen.mapper.TLabelMapper;
import com.zhuwenshen.model.TLabel;

@Service
public class LabelService {

	@Autowired
	private TLabelMapper labelMapper;
	
	public TLabel getLabelByName(String name) {
		if(StringUtils.isEmpty(name)) {
			return null;
		}
		name = name.trim();
		
		TLabel record = new TLabel();
		record.setName(name);
		
		List<TLabel> list = labelMapper.select(record);
		
		if(list == null || list.size() == 0) {
			labelMapper.insert(record);
			return record;
		}
		
		return list.get(0);		
	}
}
