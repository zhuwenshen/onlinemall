package com.zhuwenshen.aop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

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

import com.zhuwenshen.model.TUser;
import com.zhuwenshen.model.custom.User;
import com.zhuwenshen.util.MySid;

/**
 * 拦截插入，补充对创建人 、创建时间、更新人和更新时间的补充
 * 
 * @author zhuwenshen
 *
 */
@Aspect
@Component
public class InsertSqlAspect {

	private static Logger log = LoggerFactory.getLogger(InsertSqlAspect.class);
	
	@Pointcut("execution(public * com.zhuwenshen.mapper.*.insert*(..))")
	public void insert() {
	}

	@Before("insert()")
	public void deBefore(JoinPoint joinPoint) {		
		
		// 接收到请求，记录请求内容
		Object ob = joinPoint.getArgs()[0];
		if (List.class.isInstance(ob)) {
			List<?> list = (List<?>) ob;
			for (int i = 0; i < list.size(); i++) {
				setValue(list.get(i), joinPoint);
			}
		} else {
			setValue(ob, joinPoint);
		}

	}

	/**
	 * 设置值
	 * 
	 * @param o
	 * @param executingObject 
	 */
	private void setValue(Object o,JoinPoint joinPoint) {
		log.info("反射执行，插入sql设置表的创建人 、创建时间、更新人和更新时间");
		log.info("设置前对象为" + o);

		String id = null;
		// 获取id
		// 检查id是否存在
		try {
			Method getIdMethod = o.getClass().getMethod("getId");

			try {
				Object obj = getIdMethod.invoke(o);
				if (obj != null) {
					id = obj.toString();
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
			log.info("当前对象没有getId方法");

		}

		// setId
		if (id == null) {
			id = MySid.nextLong();
			//获取代理对象
			Object mapper = joinPoint.getTarget();
			while(true) {				
				try {					
					Method existsWithPrimaryKeyMethod = mapper.getClass().getMethod("existsWithPrimaryKey",Object.class);

					try {
						Object obj = existsWithPrimaryKeyMethod.invoke(mapper, id);						
						
						if (obj != null) {
							if(obj instanceof Boolean) {
								if(!(Boolean)obj) {
									break;
								}
							}else {
								log.info(existsWithPrimaryKeyMethod+"返回的类型不是Boolean");
							}
							
						}
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
					}
				} catch (NoSuchMethodException e) {
					log.info("当前对象"+mapper+"没有selectCountById方法");
					break;

				}
				//id = MySid.nextLong();
			}

			try {
				Method setIdMethod = o.getClass().getMethod("setId", String.class);

				try {
					setIdMethod.invoke(o, id);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			} catch (NoSuchMethodException e) {
				log.info("当前对象没有setId方法");

			}
		}

		// setDeleted
		try {
			Method setDeletedMethod = o.getClass().getMethod("setDeleted", Boolean.class);

			try {
				setDeletedMethod.invoke(o, false);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
			log.info("当前对象没有setDeleted方法");

		}

		// setCreateTime
		try {
			Method setCreateTimeMethod = o.getClass().getMethod("setCreateTime", Date.class);
			try {
				setCreateTimeMethod.invoke(o, new Date());
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
			log.info("当前对象没有setCreateTime方法");

		}

		// setUpdateTime
		try {
			Method setUpdateTimeMethod = o.getClass().getMethod("setUpdateTime", Date.class);
			try {
				setUpdateTimeMethod.invoke(o, new Date());
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
			log.info("当前对象没有setUpdateTime方法");

		}

		String userId = null;

		// 从redis中取		
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
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
				log.info("session无登录对象");
			}
		}

		// 如果uerId为null，而且o是TUser对象，即userId为MySid获取，即为id
		
		if (userId==null&&TUser.class.isInstance(o)) {
			//userId = id;
		} 

		// setCreateUserid
		try {
			Method setCreateUseridMethod = o.getClass().getMethod("setCreateUserid", String.class);
			try {
				setCreateUseridMethod.invoke(o, userId);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
			log.info("当前对象没有setCreateUserid方法");

		}

		// setUpdateUserid
		try {
			Method setUpdateUseridMethod = o.getClass().getMethod("setUpdateUserid", String.class);
			try {
				setUpdateUseridMethod.invoke(o, userId);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
			log.info("当前对象没有setUpdateUserid方法");

		}
	}
		
}
