package com.zhuwenshen.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zhuwenshen.exception.RedisException;
import com.zhuwenshen.model.custom.User;
import com.zhuwenshen.service.RedisService;

@Component
@WebFilter(urlPatterns = "/*",filterName = "loginFilter")
public class LoginFilter implements Filter {

	private static Logger log = LoggerFactory.getLogger(LoginFilter.class);

	@Autowired
	private RedisService redisService;

	/**
	 * 封装，不需要过滤的list列表
	 */
	// protected static List<Pattern> patterns = new ArrayList<Pattern>();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String uri = ((HttpServletRequest) request).getRequestURI();
		int u2 = uri.indexOf("/", 2);
		if (u2 == -1) {
			uri = "";
		} else {
			uri = uri.substring(u2);
		}

		
		System.out.println("uri:" + uri);
		
		//log.info("登录拦截器开始");
		//log.info("访问的地址为" + uri);

		// 不拦截登录 注册
		if (uri.startsWith("/login")) {
			chain.doFilter(request, response);
			return;
		}
		if (uri.startsWith("/register")) {
			chain.doFilter(request, response);
			return;
		}

		if (uri.startsWith("/error")) {
			chain.doFilter(request, response);
			return;
		}
		
		if (uri.startsWith("/static")) {
			chain.doFilter(request, response);
			return;
		}

		Object t = request.getAttribute("t");
		if (t == null) {
			log.debug("没有到参数t，拒绝访问");
			((HttpServletResponse) response).sendRedirect("/error/msg?msg='没有到参数t，拒绝访问'");
			return;
		}

		// redis获取user对象
		User user = null;
		try {
			user = redisService.getSession(t.toString());
		} catch (RedisException e) {
			log.debug("获取不到user对象，即还没登录，拒绝访问");

			((HttpServletResponse) response).sendRedirect("login");
			return;

		}

		// 对user的地址权限判断
		/*
		 * if(user.getUrls() != null) { if(!user.getUrls().contains(uri)) {
		 * log.debug("权限uri为"+user.getUrls()); log.debug("无权访问"); return false; } }
		 */

		// 商家拦截
		if (uri.startsWith("/m/")) {
			if (!(user.getUserType() == 4)) {
				((HttpServletResponse) response).sendRedirect("/error/msg?msg='无权限，拒绝访问'");
				return;
			}
		}

		// 管理员拦截
		if (uri.startsWith("/a/")) {
			if (!(user.getUserType() == 5 || user.getUserType() == 6)) {
				((HttpServletResponse) response).sendRedirect("/error/msg?msg='无权限，拒绝访问'");
				return;
			}
		}
		log.debug("登录拦截器结束");
		chain.doFilter(request, response);
		return;

	}

	@Override
	public void destroy() {

	}

}
