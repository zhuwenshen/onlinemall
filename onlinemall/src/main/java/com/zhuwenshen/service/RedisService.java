package com.zhuwenshen.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhuwenshen.mapper.TGlobalConstantMapper;
import com.zhuwenshen.model.TGlobalConstant;
import com.zhuwenshen.model.custom.Constant;
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
	
//	
//	/**
//	 * redis保存session
//	 * @param user
//	 * @param token
//	 * @return
//	 * @throws RedisException
//	 */
//	public String setSession(User user, String token) throws RedisException  {
//		if(user == null) {
//			throw new RedisException("redis保存对象不能为空");
//		}
//		if(StringUtils.isEmpty(token)) {
//			token = MySid.nextLong();
//		}
//		setObject(token, user, User.class, Redis.SESSION_TIME ,RedisKind.SESSION);
//		
//		return token;
//	}
//	
//	/**
//	 * redis获取session对象
//	 * @param token
//	 * @return
//	 * @throws RedisException
//	 */
//	public User getSession(String token) throws RedisException {
//		if(StringUtils.isEmpty(token)) {
//			return null;
//		}		
//		User user = getObject(token, User.class, Redis.SESSION_TIME ,RedisKind.SESSION);
//		
//		if(user == null) {
//			throw new RedisException("redis获取session对象失败");
//		}
//		
//		return user;
//	}
//	
//	/**
//	 * 删除一个session
//	 * @param token
//	 */
//	public void deleteSession(String token) {
//		if(StringUtils.isEmpty(token)) {
//			return;
//		}		
//		
//		RedisKey key = RedisKey.key(User.class, token, Redis.SESSION_TIME, RedisKind.SESSION);
//		redis.delete(key);		
//		
//	}
//		
//	
//	
//	/**
//	 * redis保存对象
//	 * @param token
//	 * @param o
//	 * @param clazz
//	 * @param kind
//	 * @return
//	 * @throws RedisException
//	 */
//	public RedisKey setObjectForever(String token, Object o ,Class<?> clazz , RedisKind kind) throws RedisException  {
//		if(o == null) {
//			throw new RedisException("redis保存对象不能为空");
//		}
//		if(StringUtils.isEmpty(token)) {
//			token = MySid.nextLong();
//		}
//		RedisKey key  = RedisKey.key(clazz, token , kind);
//		redis.setObject(key, o);
//		
//		return key;
//	}
//	
//	/**
//	 * redis保存对象
//	 * @param token
//	 * @param data
//	 * @param clazz
//	 * @param time
//	 * @param kind
//	 * @return
//	 * @throws RedisException
//	 */
//	public RedisKey setObject(String token, Object data ,Class<?> clazz , int time , RedisKind kind) throws RedisException  {
//		if(data == null) {
//			throw new RedisException("redis保存对象不能为空");
//		}
//		if(StringUtils.isEmpty(token)) {
//			token = MySid.nextLong();
//		}
//		RedisKey key  = RedisKey.key(clazz, token, time, kind);
//		redis.setObject(key, data);
//		
//		return key;
//	}
//	
//	/**
//	 * 短时间保存对象
//	 * @param token
//	 * @param data
//	 * @param clazz
//	 * @param kind
//	 * @return
//	 * @throws RedisException
//	 */
//	public RedisKey setObjectShort(String token, Object data ,Class<?> clazz , RedisKind kind) throws RedisException  {		
//		return setObject(token, data, clazz, Redis.OBJECT_TIME , kind);
//	}
//	
//	/**
//	 * 长时间保存对象
//	 * @param token
//	 * @param data
//	 * @param clazz
//	 * @param kind
//	 * @return
//	 * @throws RedisException
//	 */
//	public RedisKey setObjectLong(String token, Object data ,Class<?> clazz , RedisKind kind) throws RedisException  {		
//		return setObject(token, data, clazz, Redis.SESSION_TIME , kind);
//	}
//	
//	
//	/**
//	 * redis获取永久对象
//	 * @param token
//	 * @param clazz
//	 * @param kind
//	 * @return
//	 * @throws RedisException
//	 */
//	public <T> T getObjectForever(String token,Class<T> clazz , RedisKind kind) throws RedisException {
//		if(StringUtils.isEmpty(token)) {
//			return null;
//		}
//		
//		@SuppressWarnings("unchecked")
//		T t = (T) redis.getObject(RedisKey.key(clazz, token , kind));
//		
//		if(t == null) {
//			throw new RedisException("redis获取对象失败");
//		}
//		
//		return t;
//	}
//	
//	/**
//	 * redis获取一段时间的对象
//	 * @param token
//	 * @param clazz
//	 * @param time
//	 * @param kind
//	 * @return
//	 * @throws RedisException
//	 */
//	public <T> T getObject(String token,Class<T> clazz,int time , RedisKind kind) throws RedisException {
//		if(StringUtils.isEmpty(token)) {
//			return null;
//		}
//		
//		@SuppressWarnings("unchecked")
//		T t = (T) redis.getObjectAndActive(RedisKey.key(clazz, token,time , kind));
//		
//		if(t == null) {
//			throw new RedisException("redis获取对象失败");
//		}
//		
//		return t;
//	}
//	
//	/**
//	 * 取出短时间对象
//	 * @param token
//	 * @param clazz
//	 * @param kind
//	 * @return
//	 * @throws RedisException
//	 */
//	public <T> T getObjectShort(String token ,Class<T> clazz , RedisKind kind) throws RedisException  {		
//		return getObject(token, clazz, Redis.OBJECT_TIME, kind);
//	}
//	
//	/**
//	 * 取出长时间对象
//	 * @param token
//	 * @param clazz
//	 * @param kind
//	 * @return
//	 * @throws RedisException
//	 */
//	public  <T> T getObjectLong(String token ,Class<T> clazz , RedisKind kind) throws RedisException  {		
//		return getObject(token,  clazz, Redis.SESSION_TIME, kind);
//	}
//	
//	/**
//	 * redis获取永久List
//	 * @param token
//	 * @param clazz
//	 * @param kind
//	 * @return
//	 * @throws RedisException
//	 */
//	public <T> List<T> getListForever(String token,Class<T> clazz , RedisKind kind) throws RedisException {
//		if(StringUtils.isEmpty(token)) {
//			return null;
//		}
//				
//		List<T> list =  redis.getList(RedisKey.key(clazz, token, kind) ,clazz);
//		
//		if(list == null) {
//			throw new RedisException("redis获取对象失败");
//		}
//		
//		return list;
//	}
//
//	
//	
	
}
