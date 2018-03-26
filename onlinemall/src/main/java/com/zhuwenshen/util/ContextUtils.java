package com.zhuwenshen.util;

import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

public class ContextUtils {
	
	private static ApplicationContext ac = null;
	
	private static Environment en = null;
	
	public static String contextPath = "";

	public static void set(ApplicationContext applicationContext) {
		ac = applicationContext;
		en = ac.getEnvironment();
		contextPath = en.getProperty("my-context-path");
		if(contextPath==null) {
			contextPath = "";
		}
	}
	
	public static<T> T getBean(Class<T> clazz) {
		if(ac!=null) {
			return ac.getBean(clazz);
		}
		
		return null;
	}
	
	public static String getValue(String key) {
		return en.getProperty(key);
	}

}
