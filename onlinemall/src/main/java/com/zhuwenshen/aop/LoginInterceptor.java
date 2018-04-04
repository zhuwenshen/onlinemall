package com.zhuwenshen.aop;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zhuwenshen.model.custom.User;
import com.zhuwenshen.service.UserService;
import com.zhuwenshen.util.ContextUtils;
import com.zhuwenshen.util.IpUtils;

public class LoginInterceptor extends WebApplicationObjectSupport implements HandlerInterceptor {

	private static Logger log = LoggerFactory.getLogger(LoginInterceptor.class);	

	private UserService userService;

	/**
	 * 在请求处理之前进行调用（Controller方法调用之前）
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		

		if (userService == null) {
			userService = ContextUtils.getBean(UserService.class);
		}

		String ip = IpUtils.getIpAddr(request);
		String uri = request.getRequestURI();
		int u2 = uri.indexOf("/", 2);
		if (u2 == -1) {
			uri = "";
		} else {
			uri = uri.substring(u2);
		}		

		System.out.println("ip："+ip+"，uri:" + uri + "?" + (request.getQueryString() == null ? "" : request.getQueryString()));

		if (uri.endsWith(".js")) {				
			return true;
		}
		if (uri.endsWith(".css")) {			
			return true;
		}
		
		//获取常量
		if (uri.equals("/kindConstant")) {			
			return true;
		}
		
		//查询系统常量
		if (uri.startsWith("/c/")) {			
			return true;
		}
		
		//自动登录
		Object t = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if ("t".equals(cookies[i].getName())) {
					t = cookies[i].getValue();
				}
			}
		}		
		
		String contextPath = ContextUtils.contextPath;
		
		// session获取user对象
		User user = null;
		Object u = request.getSession().getAttribute("user");
		if(u!=null && u instanceof User) {
			user = (User)u;
		}
		if (t != null && user==null) {
			//检验ip和token，自动登录
			user = userService.getSession(t.toString(), request.getSession(),ip);
			
		}
		
		if (uri.startsWith("/login")) {
			if (user != null) {

				String nextUri = contextPath+"/index";
				switch (user.getUserType()) {
				case (1):
					nextUri = contextPath+"/index";
					break;
				case (2):
					nextUri = contextPath+"/index";
					break;
				case (3):
					nextUri = contextPath+"/m/index";
					break;
				case (4):
					nextUri = contextPath+"/m/index";
					break;
				case (5):
					nextUri = contextPath+"/a/index";
					break;
				default:
					nextUri = contextPath+"/a/index";
					break;
				}

				response.sendRedirect(nextUri);
				return false;
			}
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
		
		if (uri.startsWith("/test")) {			
			return true;
		}

		/*if (t == null) {
			log.debug("没有到参数t，拒绝访问");
			request.getRequestDispatcher("/error/msg?msg=没有到参数t，拒绝访问").forward(request, response);
			return false;
		}*/
		
		if (user == null) {
			log.debug("没有登录，拒绝访问");
			response.sendRedirect(contextPath+"/login");
			//request.getRequestDispatcher("/login").forward(request, response);
			return false;
		}

		// 对user的地址权限判断
		/*
		 * if(user.getUrls() != null) { if(!user.getUrls().contains(uri)) {
		 * log.debug("权限uri为"+user.getUrls()); log.debug("无权访问"); return false; } }
		 */

		// 商家拦截
		if (uri.startsWith("/m/")) {
			if (!(user.getUserType() == 4 || user.getUserType() == 3)) {

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
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView mv)
			throws Exception {
		log.debug("请求处理之后进行调用");
		if (mv == null)
			return;
		Object t = mv.getModel().get("t");
		
		if (t != null) {
			Cookie cookie = new Cookie("t", t.toString());
			cookie.setPath(ContextUtils.contextPath);
			// 设置cookie的存活时间
			// cookie.setMaxAge(5*60);
			response.addCookie(cookie);
		}

	}

	/**
	 * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行 （主要是用于进行资源清理工作）
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception ex)
			throws Exception {

	}

	/*private void deleteCookieT(HttpServletResponse response) {
		Cookie cookie = new Cookie("t", null);
		cookie.setPath(ContextUtils.contextPath);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}*/

}
