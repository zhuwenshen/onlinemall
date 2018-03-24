package com.zhuwenshen.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.zhuwenshen.aop.LoginInterceptor;

@SuppressWarnings("deprecation")
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		/**
		 * 拦截器按照顺序执行
		 */		
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**");
		
		//super.addInterceptors(registry);
	}

}
