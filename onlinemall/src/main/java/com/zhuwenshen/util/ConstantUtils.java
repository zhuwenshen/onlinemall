package com.zhuwenshen.util;

import com.zhuwenshen.model.TGlobalConstant;
import com.zhuwenshen.service.ConstantService;

public class ConstantUtils {
	
	private static ConstantService constantService = null;
	
	public static String SYSTEM_KIND = "system";
	
	public static String key(TGlobalConstant gc) {
		return Redis.CONSTANT_PREFIX+gc.getKind()+"-"+gc.getName();
	}
	
	public static String key(String kind, String name) {
		return Redis.CONSTANT_PREFIX+kind+"-"+name;
	}
	
	/**
	 * 获取默认头像（完整地址）
	 * @return
	 */
	public static String HEAD_PORTRAIT_DEFAULT_URL() {
		checkConstantService();
		return constantService.getConstantvalue(SYSTEM_KIND, "head_portrait_default_url");
	}
	
	/**
	 * 图片存储服务器ip
	 * @return
	 */
	public static String SERVER_PIC_IP() {
		checkConstantService();
		return constantService.getConstantvalue(SYSTEM_KIND, "server_pic_ip");
	}
	
	public static String MERCHANT_NUM() {
		checkConstantService();
		return constantService.getConstantvalueWithNoCach(SYSTEM_KIND, "merchant_num");
	}
	
	private static void checkConstantService() {
		if(constantService == null) {
			constantService = ContextUtils.getBean(ConstantService.class);
		}
	}
	

}
