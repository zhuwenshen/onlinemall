package com.zhuwenshen.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zhuwenshen.exception.RedisException;
import com.zhuwenshen.model.custom.User;
import com.zhuwenshen.service.RedisService;


public class LoginInterceptor implements HandlerInterceptor{
	
	private static Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

	@Autowired
	private RedisService redisService;

	/**
	 * 在请求处理之前进行调用（Controller方法调用之前）
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object object) throws Exception {		
		
		
		String uri = request.getRequestURI();
		System.out.println("***uri:" + uri);
		int u2 = uri.indexOf("/", 2);
		if (u2 == -1) {
			uri = "";
		} else {
			uri = uri.substring(u2);
		}

		
		System.out.println("uri:" + uri);

		// 不拦截登录 注册
		if (uri.startsWith("/login")) {
			
			return true;
		}
		if (uri.startsWith("/register")) {
			return true;
		}

		if (uri.startsWith("/error")) {
			return true;
		}
		
		if (uri.startsWith("/static")) {
			return true;
		}

		Object t = request.getAttribute("t");
		if (t == null) {
			log.debug("没有到参数t，拒绝访问");			
			request.getRequestDispatcher("/error/msg?msg=没有到参数t，拒绝访问").forward(request, response);
			return false;
		}

		// redis获取user对象
		User user = null;
		try {
			user = redisService.getSession(t.toString());
		} catch (RedisException e) {
			log.debug("获取不到user对象，即还没登录，拒绝访问");
			response.sendRedirect("login");
			return false;

		}

		// 对user的地址权限判断
		/*
		 * if(user.getUrls() != null) { if(!user.getUrls().contains(uri)) {
		 * log.debug("权限uri为"+user.getUrls()); log.debug("无权访问"); return false; } }
		 */

		// 商家拦截
		if (uri.startsWith("/m/")) {
			if (!(user.getUserType() == 4)) {
				request.getRequestDispatcher("/error/msg?msg=无权限，拒绝访问").forward(request, response);
				return false;
			}
		}

		// 管理员拦截
		if (uri.startsWith("/a/")) {
			if (!(user.getUserType() == 5 || user.getUserType() == 6)) {
				request.getRequestDispatcher("/error/msg?msg=无权限，拒绝访问").forward(request, response);
				return false;
			}
		}
		log.debug("登录拦截器结束");
			
		return true;
	}
	
	/**
	 * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
			Object object, ModelAndView mv)
			throws Exception {
		
	}
	
	/**
	 * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行
	 * （主要是用于进行资源清理工作）
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
			Object object, Exception ex)
			throws Exception {
		
	}
	
	
}
