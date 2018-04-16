package com.zhuwenshen;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.zhuwenshen.model.custom.ClassDetail;
import com.zhuwenshen.model.custom.GoodsClass;

public class TestJson {
	
	public static void main(String[] args) {
		GoodsClass c = new GoodsClass();
		c.setName("1346");
		
		List<ClassDetail> list = new ArrayList<>();
		ClassDetail d1 =  new ClassDetail();
		d1.setName("ddd");
		ClassDetail d2 =  new ClassDetail();
		d2.setName("bbb");
		ClassDetail d3 =  new ClassDetail();
		d3.setName("ccc");
		list.add(d1);
		list.add(d2);
		list.add(d3);
		
		c.setDetails(list);
		
		String json = JSON.toJSONString(c);
		System.out.println(json);
	}
}
