package com.zhuwenshen;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.zhuwenshen.model.custom.User;

public class Test2 {
	
/*	public static void main(String[] args) {
		List<User> list =  new ArrayList<User>();
		list.add(new User());
		
		
		Type genericType = list.getClass().getGenericSuperclass();   
		System.out.println(genericType);
        if(genericType != null) {
        	 // 如果是泛型参数的类型     
            if(genericType instanceof ParameterizedType){     
                ParameterizedType pt = (ParameterizedType) genericType;  
                //得到泛型里的class类型对象    
                Class<?> genericClazz = (Class<?>)pt.getActualTypeArguments()[0];   
                System.out.println(genericClazz);
            } 
        }
       
	}*/

}
