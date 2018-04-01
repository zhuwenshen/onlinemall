package com.zhuwenshen.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhuwenshen.model.TUser;
import com.zhuwenshen.util.SqlUtils;

@RestController
public class TestController {
	
	@GetMapping("test/a")
	public String testContextUtils() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		TUser u = new TUser();
		//Class<? extends TUser> clazz = u.getClass();
		//System.out.println(clazz.getSimpleName());
		Object mapper = SqlUtils.getMapper(u);
		
		Method selectCountByIdMethod = mapper.getClass().getMethod("existsWithPrimaryKey",Object.class);
		Object obj = selectCountByIdMethod.invoke(mapper, "201803232308HFDMSNXC4K");
		System.out.println(obj);		
		return "成功";
	}

}
