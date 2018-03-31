package com.zhuwenshen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.zhuwenshen.util.ContextUtils;

import tk.mybatis.spring.annotation.MapperScan;

@ImportResource(locations = {"classpath:spring.xml"})
@SpringBootApplication
@MapperScan(basePackages = "com.zhuwenshen.mapper")
//扫描 所有需要的包, 包含一些自用的工具类包 所在的路径
@ComponentScan(basePackages= {"com.zhuwenshen"})
//开启定时任务
@EnableScheduling
//开启异步调用方法
@EnableAsync
public class Application {
	
	
	public static void main(String[] args) {
		final ApplicationContext applicationContext =  SpringApplication.run(Application.class, args);
		ContextUtils.setApplicationContext(applicationContext);
	}	
}
