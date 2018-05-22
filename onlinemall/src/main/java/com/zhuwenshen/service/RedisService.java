package com.zhuwenshen.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.zhuwenshen.exception.RedisException;
import com.zhuwenshen.mapper.TGlobalConstantMapper;
import com.zhuwenshen.model.TGlobalConstant;
import com.zhuwenshen.model.custom.Constant;
import com.zhuwenshen.model.custom.User;
import com.zhuwenshen.util.ConstantUtils;
import com.zhuwenshen.util.Redis;

/**
 * 缓存redis
 * 格式：
 * 1.缓存对象 ：key：c-*****
 * @author zhuwenshen
 *
 */
@Service
public class RedisService {

	@Autowired
	private Redis redis;
	
	@Autowired
	private TGlobalConstantMapper gcm;
	
	/**
	 * 缓存一个缓存对象
	 * @param key
	 * @param data
	 */
	public void setCachObject(String key, Object data ) {
		redis.setObject(key, data, Redis.CACH_TIME);
	}
	
	/**
	 * 获取缓存对象
	 * @param key
	 * @param clazz
	 * @param isList
	 * @return
	 */
	public Object getCachObject(String key , Class<?> clazz, Boolean isList) {
		return redis.getObjectAndActive(key, Redis.CACH_TIME, clazz, isList);
	}
	
	public void flushCach() {
		redis.deleteBatch(Redis.CACH_PREFIX+"*");
	}
	
	/**
	 * 缓存所有静态常量 
	 */
	public void cachAllConstant() {
		//先删除所有常量
		this.deletedAllConstant();
		
		TGlobalConstant gc = new TGlobalConstant();
		gc.setDeleted(false);
		gc.setUseful(true);
		List<TGlobalConstant> list = gcm.select(gc);
		
		//永久保存静态变量
		for (TGlobalConstant g : list) {
			redis.setObject(ConstantUtils.key(g), g.getValue1(), Redis.PERMANENT_TIME);
		}
	}
	
	/**
	 * 更新一个常量
	 * @param gc
	 */
	public void setConstant(TGlobalConstant gc) {
		if(gc.getUseful()) {
			redis.setObject(ConstantUtils.key(gc), gc.getValue1(), Redis.PERMANENT_TIME);
		}		
	}
	
	/**
	 * 更新一个常量
	 * @param gc
	 */
	public void setConstant(Constant c) {
		redis.setObject(ConstantUtils.key(c.getKind(),c.getName()), c.getValue(), Redis.PERMANENT_TIME);
	}
	
	/**
	 * 获取一个常量
	 * @param kind
	 * @param name
	 * @return
	 */
	public Constant getConstant(String kind , String name) {
		Object value = redis.getObject(ConstantUtils.key(kind, name), String.class, false);
		if(value == null) {
			return null;
		}
		return new Constant(kind, name, value.toString());
	}
	
	/**
	 * 获取一个常量值
	 * @param kind
	 * @param name
	 * @return
	 */
	public String getConstantvalue(String kind , String name) {
		Object value = redis.getObject(ConstantUtils.key(kind, name), String.class, false);
		if(value == null) {
			return null;
		}
		return value.toString();
	}
	
	/**
	 * 获取一类常量
	 * @param kind
	 * @return
	 */
	public List<Constant> getConstantBykind(String kind){
		List<Constant> list = new ArrayList<Constant>();
		Set<String> set = redis.key(Redis.CONSTANT_PREFIX+kind+"-*");
		String name = null;
		for (String key : set) {
			name = key.split("-")[2];
			list.add(this.getConstant(kind, name));
		}
		
		return list;
	}
	
	/**
	 * 删除单个常量
	 * @param gc
	 */
	public void deletedConstant(TGlobalConstant gc) {
		redis.delete(ConstantUtils.key(gc));
	}
	
	/**
	 * 批量删除
	 * @param glist
	 */
	public void deletedBatchConstant(List<TGlobalConstant> glist) {
		for (TGlobalConstant tGlobalConstant : glist) {
			this.deletedConstant(tGlobalConstant);
		}
	}
	
	/**
	 * 删除所有常量缓存
	 * @param glist
	 */
	public void deletedAllConstant() {
		redis.deleteBatch(Redis.CONSTANT_PREFIX+"*");		
	}
	
	/**
	 * redis获取session对象
	 * 
	 * @param token
	 * @param ip
	 *            ip地址
	 * @return
	 * @throws RedisException
	 */
	public User getSeccion(String token, String ip) throws RedisException {
		if (StringUtils.isEmpty(token) || StringUtils.isEmpty(ip)) {
			return null;
		}
		User user = (User) redis.getObjectAndActive(getSessionKey(token, ip), Redis.SESSION_TIME, User.class, false);

		if (user == null) {
			throw new RedisException("redis获取session对象失败");
		}

		return user;

	}

	/**
	 * 设置session
	 * 
	 * @param token
	 * @param ip
	 * @param u
	 * @throws RedisException 
	 */
	public void setSeccion(String token, String ip, User user) throws RedisException {
		if (StringUtils.isEmpty(token) || StringUtils.isEmpty(ip) || user==null) {
			throw new RedisException("缓存到redis中的参数不能为空");
		}
		
		redis.setObject(getSessionKey(token, ip), user, Redis.SESSION_TIME);

	}

	/**
	 * 获取redis的session的key ： key： t-ip-****
	 * 
	 * @param token
	 * @param ip
	 * @return
	 */
	public static String getSessionKey(String token, String ip) {
		if (StringUtils.isEmpty(token) || StringUtils.isEmpty(ip)) {
			return null;
		}

		return "t-" + ip + "-" + token;
	}

	/**
	 * 删除session
	 * @param token
	 * @param ip
	 */
	public void deleteSession(String token, String ip) {
		String key = getSessionKey(token, ip);
		if(key == null) {
			return ;
		}
		redis.delete(key);
		
	}
	
}
