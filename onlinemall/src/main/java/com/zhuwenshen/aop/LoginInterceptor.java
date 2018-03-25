package com.zhuwenshen.aop;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zhuwenshen.model.custom.User;
import com.zhuwenshen.service.UserService;
import com.zhuwenshen.util.ContextUtil;

public class LoginInterceptor extends WebApplicationObjectSupport implements HandlerInterceptor {

	private static Logger log = LoggerFactory.getLogger(LoginInterceptor.class);	

	private UserService userService;

	/**
	 * 在请求处理之前进行调用（Controller方法调用之前）
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		

		if (userService == null) {
			userService = ContextUtil.getBean(UserService.class);
		}

		String uri = request.getRequestURI();
		int u2 = uri.indexOf("/", 2);
		if (u2 == -1) {
			uri = "";
		} else {
			uri = uri.substring(u2);
		}

		System.out.println("uri:" + uri + "?" + (request.getQueryString() == null ? "" : request.getQueryString()));

		Object t = null;

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if ("t".equals(cookies[i].getName())) {
					t = cookies[i].getValue();
				}
			}
		}

		Object t2 = request.getParameter("t");
		if (t2 == null) {
			if (t != null) {
				request.setAttribute("t", t);
			}
		} else if (t == null) {
			t = t2;
		}

		// redis获取user对象
		User user = null;
		if (t != null) {

			user = userService.getSession(t.toString());
			if (user == null) {
				log.debug("获取不到user对象，即还没登录，拒绝访问");
				deleteCookieT(response);
				request.removeAttribute("t");
				response.sendRedirect("/login");
				return false;
			}
		}

		if (uri.startsWith("/login")) {
			if (user != null) {

				String nextUri = "index";
				switch (user.getUserType()) {
				case (1):
					nextUri = "index";
					break;
				case (2):
					nextUri = "index";
					break;
				case (3):
					nextUri = "m/index";
					break;
				case (4):
					nextUri = "m/index";
					break;
				case (5):
					nextUri = "a/index";
					break;
				default:
					nextUri = "a/index";
					break;
				}

				request.getRequestDispatcher(nextUri + "?t=" + t.toString()).forward(request, response);

				return false;
			}
			return true;
		}

		if (uri.startsWith("/register")) {
			deleteCookieT(response);
			request.removeAttribute("t");
			return true;
		}

		if (uri.startsWith("/error")) {
			return true;
		}

		if (uri.startsWith("/static")) {
			return true;
		}

		if (t == null) {
			log.debug("没有到参数t，拒绝访问");
			request.getRequestDispatcher("/error/msg?msg=没有到参数t，拒绝访问").forward(request, response);
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
		if (t == null) {
			t = request.getParameter("t");
			if (t instanceof String) {
				if (!StringUtils.isEmpty(t)) {
					mv.getModel().put("t", t);
				}
			}
		}
		if (t != null) {
			Cookie cookie = new Cookie("t", t.toString());
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

	private void deleteCookieT(HttpServletResponse response) {
		Cookie cookie = new Cookie("t", null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}

}
