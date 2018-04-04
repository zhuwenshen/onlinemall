package com.zhuwenshen.aop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.serializer.BeanContext;
import com.alibaba.fastjson.serializer.ContextValueFilter;
import com.zhuwenshen.annotation.ConstantTranslate;
import com.zhuwenshen.service.ConstantService;
import com.zhuwenshen.util.ContextUtils;

public class ConstantTranslateFilter implements ContextValueFilter {
	
	private Logger log = LoggerFactory.getLogger(ConstantTranslateFilter.class);
	
	private ConstantService cs = null;
	
	@Override
	public Object process(BeanContext context, Object object, String name, Object value) {
		ConstantTranslate ct = context.getAnnation(ConstantTranslate.class);
		if(ct!=null) {
			checkConstantService();
			Method method = null;
			try {
				method = context.getBeanClass().getMethod(ct.valueMethod());
			} catch (NoSuchMethodException e) {				
				e.printStackTrace();
				return value;
			} catch (SecurityException e) {				
				e.printStackTrace();
				return value;
			}
			if(method!=null) {
				Object val;
				try {
					val = method.invoke(object);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					return value;
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					return value;
				} catch (InvocationTargetException e) {
					e.printStackTrace();
					return value;
				}
				value = cs.getNameByKindAndValue(ct.kind(), val.toString());
			}
			
		}
		return value;
	}
	
	private void checkConstantService() {
		if(cs == null) {
			cs = ContextUtils.getBean(ConstantService.class);
			if(cs == null) {
				log.error("spring获取ConstantService失败");
			}
		}
		
	}

}
