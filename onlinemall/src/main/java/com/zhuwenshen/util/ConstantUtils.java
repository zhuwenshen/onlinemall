package com.zhuwenshen.util;

import com.zhuwenshen.model.TGlobalConstant;

public class ConstantUtils {
	
	
	
	public static String key(TGlobalConstant gc) {
		return Redis.CONSTANT_PREFIX+gc.getKind()+"-"+gc.getName();
	}
	
	public static String key(String kind, String name) {
		return Redis.CONSTANT_PREFIX+kind+"-"+name;
	}
	
	

}
