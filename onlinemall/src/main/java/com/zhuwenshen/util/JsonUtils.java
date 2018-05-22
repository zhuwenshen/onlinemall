package com.zhuwenshen.util;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zhuwenshen.aop.ConstantTranslateFilter;
import com.zhuwenshen.aop.PicUrlFilter;

public class JsonUtils {	


	private static SerializeFilter[] filters = {new ConstantTranslateFilter(), new PicUrlFilter()};

	/**
	 * 将对象转换成json字符串	
	 * @param data
	 * @return
	 */
	public static String toJSONString(Object data) {
		try {
			String string = JSON.toJSONString(data, filters,  SerializerFeature.WriteMapNullValue);
			return string;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	public static String toJSONStringWithoutFilters(Object data) {
		try {
			String string = JSON.toJSONString(data,  SerializerFeature.WriteMapNullValue);
			return string;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将json结果集转化为对象
	 * 
	 * @param jsonData  json数据
	 * @param clazz  对象中的object类型
	 * @return
	 */
	public static <T> T parseObject(String jsonData, Class<T> beanType) {
		try {
			T t = JSON.parseObject(jsonData, beanType);
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将json数据转换成pojo对象list 
	 * @param jsonData
	 * @param beanType
	 * @return
	 */
	public static <T> List<T> parseArray(String jsonData, Class<T> beanType) {
		try {
			List<T> list =JSON.parseArray(jsonData, beanType);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
