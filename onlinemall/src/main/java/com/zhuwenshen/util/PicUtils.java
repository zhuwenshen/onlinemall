package com.zhuwenshen.util;

import org.springframework.util.StringUtils;

public class PicUtils {	
	
	public static String addPicIp(String url) {
		if(StringUtils.isEmpty(url)) {
			return url;
		}
		
		if(url.startsWith("http://")) {
			return url;
		}else  {
			return ConstantUtils.SERVER_PIC_IP()+url;
		}
	} 
}
