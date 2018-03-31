package com.zhuwenshen.util;

import org.springframework.context.ApplicationContext;

/**
 * 以静态变量保存Spring ApplicationContext, 可在任何代码任何地方任何时候中取出ApplicaitonContext.
 */
public class ContextUtils{
	
	private static ApplicationContext ac = null;
	
	//项目名
	public static String contextPath = "";	
	
	/**
     * 实现ApplicationContextAware接口的context注入函数, 将其存入静态变量.
     */
	public static void setApplicationContext(ApplicationContext applicationContext) {
    	ac = applicationContext; // NOSONAR
    	contextPath = applicationContext.getEnvironment().getProperty("my-context-path");
    	if(contextPath==null) {
			contextPath = "";
		}
    	
    	System.out.println("ApplicationContext注入成功"+ac);
    }

    /**
     * 取得存储在静态变量中的ApplicationContext.
     */
    public static ApplicationContext getApplicationContext() {
        checkApplicationContext();
        return ContextUtils.ac;
    }

    /**
     * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
   
    @SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
        checkApplicationContext();
        return (T) ac.getBean(name);
    }

    /**
     * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    
    public static <T> T getBean(Class<T> clazz) {
        checkApplicationContext();
        return (T) ac.getBean(clazz);
    }  

    private static void checkApplicationContext() {
        if (ac == null) {
            throw new IllegalStateException("applicaitonContext未注入,请在spring.xml中定义ContextUtils");
        }
    }

}
