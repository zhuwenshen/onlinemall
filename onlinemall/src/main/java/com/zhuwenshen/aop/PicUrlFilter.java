package com.zhuwenshen.aop;

import com.alibaba.fastjson.serializer.BeanContext;
import com.alibaba.fastjson.serializer.ContextValueFilter;
import com.zhuwenshen.annotation.PicUrl;
import com.zhuwenshen.util.PicUtils;

public class PicUrlFilter implements ContextValueFilter {

	@Override
	public Object process(BeanContext context, Object object, String name, Object value) {
		PicUrl url= context.getAnnation(PicUrl.class);
		if(url!=null&& value!=null) {
			
			return PicUtils.addPicIp(value.toString());
		}
		return value;
	}

}
