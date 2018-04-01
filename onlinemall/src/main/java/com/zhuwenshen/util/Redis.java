package com.zhuwenshen.util;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class Redis {
	
	public static int SESSION_TIME = 1*60*60;//session存活时间
	
	public static int CACH_TIME = 5*60;//普通对象存活时间
	//永久保存对象
	public static Integer PERMANENT_TIME = -1;
	//缓存对象前缀
	public static String CACH_PREFIX = "c-";
	//常量值对象前缀
	public static String CONSTANT_PREFIX = "g-";
	
	

	@Autowired
	private JedisPool jedisPool;
	

	/**
	 * 获取key的value值
	 * 
	 * @param key
	 * @return
	 */
	private String get(String key) {
		Jedis jedis = jedisPool.getResource();
		String str = null;
		try {
			str = jedis.get(key);
		} finally {
			try {
				jedis.close();
			} catch (Exception e) {
				e.printStackTrace();
				return str;
			}
		}
		return str;
	}

	/**
	 * 保存一个值
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	private String set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		String str = null;
		try {
			str = jedis.set(key, value);
		} finally {
			try {
				jedis.close();
			} catch (Exception e) {
				e.printStackTrace();
				return str;
			}
		}
		return str;
	}
	
	/**
	 * 实现命令：SET key value EX seconds，设置key-value和超时时间（秒）
	 * 
	 * @param key
	 * @param value
	 * @param timeout
	 *            （以秒为单位）
	 */
	private String set(String key, int seconds, String value) {
		Jedis jedis = jedisPool.getResource();
		String str = null;
		try {
			str = jedis.setex(key, seconds, value);
		} finally {
			try {
				jedis.close();
			} catch (Exception e) {
				e.printStackTrace();
				return str;
			}
		}
		return str;
	}	

	/**
	 * 实现命令：expire 设置过期时间，单位秒
	 * 
	 * @param key
	 * @return
	 */
	public void expire(String key, int seconds) {
		Jedis jedis = jedisPool.getResource();
		try {
			jedis.expire(key, seconds);
		} finally {
			try {
				jedis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 保存一个对象 
	 * @param key
	 * @param data
	 * @param seconds 小于等于0为永久保存
	 */
	public void setObject(String key, Object data , int seconds) {	
		if(seconds <= 0) {
			set(key.toString(), JsonUtils.objectToJson(data));	
		}else {
			set(key.toString(), seconds, JsonUtils.objectToJson(data));
		}
			
	}	
	
	/**
	 * 获取一个对象
	 * @param key
	 * @param clazz
	 * @param isList
	 * @return
	 */
	public Object getObject(String key, Class<?> clazz , Boolean isList) {
		String data = get(key.toString());
		Object t = null;
		try {
			if(!StringUtils.isEmpty(data)) {
				if(isList) {
					t = JsonUtils.jsonToList(data, clazz);
				}else {
					t =  JsonUtils.jsonToPojo(data, clazz);
				}				 
			}
		} catch (Exception e) {			
			e.printStackTrace();
			return null;
		}
		return t;
	}
	
	/**
	 * 获取一个对象，并且让它继续存活一段时间
	 * @param key
	 * @param seconds 小于0为
	 * @param clazz
	 * @param isList
	 * @return
	 */
	public Object getObjectAndActive(String key ,int seconds , Class<?> clazz, Boolean isList) {		
		Jedis jedis = jedisPool.getResource();
		String data = "";
		Object t = null;
		try {
			data = jedis.get(key.toString());
			jedis.expire(key, seconds);
			if(!StringUtils.isEmpty(data)) {
				if(isList) {
					t = JsonUtils.jsonToList(data, clazz);
				}else {
					t =  JsonUtils.jsonToPojo(data, clazz);
				}				 
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				jedis.close();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		return t;
	}
	
	/**
	 * 删除一个对象
	 * @param key
	 */
	public void delete(String key) {
		Jedis jedis = jedisPool.getResource();
		try {
			jedis.del(key);
			
		}catch(Exception e) {
			e.printStackTrace();			
		} finally {
			try {
				jedis.close();
			} catch (Exception e) {
				e.printStackTrace();				
			}
		}
		
	}
	
	/**
	 * 批量删除对象
	 * @param key
	 */
	public void deleteBatch(String pattern) {
		Jedis jedis = jedisPool.getResource();
		try {
			Set<String> keys = jedis.keys(pattern);
			for(String key : keys) {
				jedis.del(key);
			}			
			
		}catch(Exception e) {
			e.printStackTrace();			
		} finally {
			try {
				jedis.close();
			} catch (Exception e) {
				e.printStackTrace();				
			}
		}
		
	}
	
	public Set<String> key(String pattern) {
		Jedis jedis = jedisPool.getResource();
		Set<String> keys = null;
		try {
			keys = jedis.keys(pattern);						
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				jedis.close();
			} catch (Exception e) {
				e.printStackTrace();	
				return null;
			}
		}
		return keys;
	}
	
	
}
