package com.zhuwenshen.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtils {
	private static final SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 

	public static String formatToDateAndTime(Date date) {
		return format0.format(date);
	}
}
