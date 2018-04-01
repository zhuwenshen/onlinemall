package com.zhuwenshen.util;

import java.lang.annotation.Annotation;

import javax.persistence.Table;

public class SqlUtils {

	/**
	 * 通过model实体类获取表名
	 * @param object
	 * @return
	 */
	public static String getTableName(Object object) {
		Class<?> clazz = object.getClass();		
		Annotation[] list = clazz.getAnnotations();
		for (Annotation annotation : list) {
			if(annotation instanceof Table) {
				return ((Table) annotation).name();
			}
		}
		return "";
	}
	
	public static Object getMapper(Object o) {
		Object mapper = ContextUtils.getBean(o.getClass().getSimpleName()+"Mapper");
		
		/*System.out.println(mapper);
		for (Method method : mapper.getClass().getMethods()) {
			System.out.println(method);
		}*/
		return mapper;
	}
}
