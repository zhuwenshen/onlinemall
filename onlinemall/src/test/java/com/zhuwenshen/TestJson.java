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
	
	/*public static void main(String[] args) {
		Date d =  new Date(System.currentTimeMillis()+(long)(30*24*60*60*1000));
		System.out.println(d);
		System.out.println(System.currentTimeMillis());
		System.out.println("t:"+((long)30*24*60*60*1000));
		long  l =  System.currentTimeMillis()-((long)30*24*60*60*1000);
		System.err.println("l:"+l);
		System.out.println(new Date(l));
	}*/
}
