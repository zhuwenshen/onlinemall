package com.zhuwenshen.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 当前session工具类
 * @author zhuwenshen
 *
 */
public class GlobalSession {

	private static final Map<String,HttpSession> sessionList = new HashMap<>();
	
	public static void  addSession(String token , HttpSession session) {
		sessionList.put(token, session);
	}
	
	public static HttpSession  getSession(String token) {
		return sessionList.get(token);
	}
	
	/**
	 * 无效
	 * @param token
	 */
	public static void invalidateSession(String token) {
		if(getSession(token)!= null) {
			try {
				getSession(token).invalidate();
			} catch (Exception e) {
				return;
			}
		}
		
		sessionList.remove(token);
		
		//TODO 通知用户被踢下线
	}
	
	
	public static void setSessionAttribute(String arg, Object o) {
		HttpSession session = getHttpSession();		
		session.setAttribute(arg, o);
	}

	public static Object getSessionAttribute(String arg) {
		HttpSession session = getHttpSession();
		return session.getAttribute(arg);
	}

	public static void removeSessionAttribute(String arg) {
		HttpSession session = getHttpSession();
		if (getSessionAttribute(arg) != null) {
			session.removeAttribute(arg);
		}
	}

	public static void setAttribute(String arg, Object o) {
		HttpSession session = getHttpSession();
		session.setAttribute(arg, o);
	}

	public static Object getAttribute(String arg) {
		HttpSession session = getHttpSession();
		return session.getAttribute(arg);
	}

	public static void removeAttribute(String arg) {
		if (getAttribute(arg) != null) {
			getHttpSession().removeAttribute(arg);
		}
	}

	public static HttpSession getHttpSession() {
		HttpServletRequest session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return session.getSession();
	}
}
