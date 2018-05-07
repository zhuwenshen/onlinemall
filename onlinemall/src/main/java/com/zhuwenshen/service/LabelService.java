package com.zhuwenshen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.zhuwenshen.exception.JsonResultException;
import com.zhuwenshen.mapper.LabelMapperCustom;
import com.zhuwenshen.mapper.TLabelMapper;
import com.zhuwenshen.model.TLabel;
import com.zhuwenshen.model.custom.JsonResult;
import com.zhuwenshen.model.custom.QueryLabel;

@Service
public class LabelService {

	@Autowired
	private TLabelMapper labelMapper;
	
	@Autowired
	private LabelMapperCustom labelMC;
	
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

	public JsonResult get1Level() {
		List<QueryLabel> list = labelMC.selectByPid(null);
		return JsonResult.ok("", list);
	}

	@Transactional
	public JsonResult add(String labelId1, String[] addLabelName) throws JsonResultException {
		if(StringUtils.isEmpty(labelId1)) {
			throw new JsonResultException(JsonResult.fail("请选择1级标签"));
		}
		
		TLabel label1 = labelMapper.selectByPrimaryKey(labelId1);
		if(label1 == null) {
			throw new JsonResultException(JsonResult.fail("请选择正确1级的标签"));
		}
		
		QueryLabel r = new QueryLabel();
		r.setId(label1.getId());
		r.setAllName(label1.getAllName());
		r.setName(label1.getName());
		r.setpId(label1.getpId());
		
		QueryLabel temp = null;
		
		if(addLabelName == null) {
			return JsonResult.ok("操作成功",r);
		}
		for(String name:addLabelName) {
			if(!StringUtils.isEmpty(name)) {
				name = name.trim();
				temp = labelMC.selectByPidAndName(r.getId(), name);
				if(temp == null) {
					//插入数据
					label1 = new TLabel();
					label1.setName(name);
					label1.setAllName(r.getAllName()+" " + name);
					label1.setpId(r.getId());
					
					labelMapper.insert(label1);		
					
					r.setId(label1.getId());
					r.setAllName(label1.getAllName());
					r.setName(label1.getName());
					r.setpId(label1.getpId());
				}else {
					r = temp;
				}			
			}
		}
		
		return JsonResult.ok("操作成功",r);
	}

	public JsonResult getLabelById(String labelId) throws JsonResultException {
		if(StringUtils.isEmpty(labelId)) {
			throw new JsonResultException(JsonResult.fail("labelId不能为空"));
		}
		
		QueryLabel l = labelMC.selectById(labelId);
		if(l == null) {
			throw new JsonResultException(JsonResult.fail("无数据"));
		}
		
		QueryLabel p = null;
		
		while(l.getpId() != null) {
			l.setParent(labelMC.selectById(l.getpId()));
			if(l.getParent()!=null) {
				p = l.getParent();
				p.setChild(l);
				l = p;
			}else {
				break;
			}
		}
		
		return JsonResult.ok("查询成功", l);
	}
}
