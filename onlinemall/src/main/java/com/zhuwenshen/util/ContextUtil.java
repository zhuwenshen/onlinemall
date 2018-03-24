package com.zhuwenshen.util;

import org.springframework.context.ApplicationContext;

public class ContextUtil {
	
	private static ApplicationContext ac = null;

	public static void set(ApplicationContext applicationContext) {
		ac = applicationContext;
	}
	
	public static<T> T getBean(Class<T> clazz) {
		if(ac!=null) {
			return ac.getBean(clazz);
		}
		
		return null;
	}

}
