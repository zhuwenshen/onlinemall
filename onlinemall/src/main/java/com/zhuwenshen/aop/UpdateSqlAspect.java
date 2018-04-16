package com.zhuwenshen.aop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zhuwenshen.model.custom.User;

/**
 * 拦截更新，补充对创建人 、创建时间、更新人和更新时间的补充
 * 
 * @author zhuwenshen
 *
 */
@Aspect
@Component
public class UpdateSqlAspect {

	private static Logger log = LoggerFactory.getLogger(UpdateSqlAspect.class);	
	
	@Pointcut("execution(public * com.zhuwenshen.mapper.*.update*(*||*,*))")
	public void update() {
	}	

	@Before("update()")
	public void deBefore(JoinPoint joinPoint) {
		
		Object o = joinPoint.getArgs()[0];				
		
		log.trace("反射执行，更新sql设置表的更新人和更新时间");
		log.trace("设置前对象为" + o);				

		// setUpdateTime
		try {
			Method setUpdateTimeMethod = o.getClass().getMethod("setUpdateTime", Date.class);
			try {
				setUpdateTimeMethod.invoke(o, new Date());
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
			log.trace("当前对象没有setUpdateTime方法");

		}

		String userId = null;
		
		// 从redis中取
		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		/*Object t = session.getAttribute("t");
		if (t != null) {
			try {
				userId = redisService.getSession(t.toString()).getId();
			} catch (RedisException e) {
				log.info("redis无登录对象");
			}
		}*/
		Object user = request.getSession().getAttribute("user");
		if (user != null) {
			try {
				userId = ((User)user).getId();
			} catch (Exception e) {
				log.trace("session无登录对象");
			}
		}
		
		
		// setUpdateUserid
		if(userId != null) {
			try {
				Method setUpdateUseridMethod = o.getClass().getMethod("setUpdateUserid", String.class);
				try {
					setUpdateUseridMethod.invoke(o, userId);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			} catch (NoSuchMethodException e) {
				log.trace("当前对象没有setUpdateUserid方法");

			}
		}
	}
}
