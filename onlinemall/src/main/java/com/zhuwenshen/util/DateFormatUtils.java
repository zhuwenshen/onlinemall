package com.zhuwenshen.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtils {
	private static final SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	
	private static final SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd"); 

	public static String formatToDateAndTime(Date date) {
		return format0.format(date);
	}
	
	/**
	 * 返回 yyyy-MM-dd 00:00:00
	 * @return
	 */
	public static Date getToday() {
		String time = format1.format(new Date());
		
		try {
			return format1.parse(time);
		} catch (ParseException e) {
			return null;
		}
	}
	
}
