package com.zhuwenshen.util;

import java.lang.annotation.Annotation;
import java.util.List;

import javax.persistence.Table;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;


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
	
	public static String shortParameterMappings(List<ParameterMapping> list) {
		if(list == null || list.size()==0) {
			return "[]";
		}
		String rs = "";
		for (ParameterMapping parameterMapping : list) {
			rs+= parameterMapping.getProperty()+",";
		}			
		
		return rs;		
	}
	
	/**
	 * 返回18位的key （c- 开头的）
	 * @param boundSql
	 * @return
	 */
	public static String getRedisCachKey(BoundSql boundSql) {
		if(boundSql == null ) return "";
		//不缓存查询语句
		if("SELECT LAST_INSERT_ID()".compareToIgnoreCase(boundSql.getSql()) == 0) {
			return "";
		}		
		String rs = "{";
		try {
			long st = System.currentTimeMillis();
			String json = JsonUtils.toJSONStringWithoutFilters(boundSql.getParameterObject());
			System.out.println((System.currentTimeMillis()-st)+"ms，fastjson装换为redis key："+json);
			rs+="["+boundSql.getSql()+"],";
			rs+="["+shortParameterMappings(boundSql.getParameterMappings())+"],";
			rs+="["+json+"],";
			rs+="}";
			rs = Redis.CACH_PREFIX+MD5Utils.md5Hex(rs);
		} catch (Exception e) {			
			e.printStackTrace();
			return null;
		}
		return rs;
		
	}
}
