package com.zhuwenshen.util;

//@Component
public class Redis {
	
	/*public static int SESSION_TIME = 1*60*60;//session存活时间
	
	public static int OBJECT_TIME = 5*60;//普通对象存活时间

	@Autowired
	private JedisPool jedisPool;
	

	*//**
	 * 获取key的value值
	 * 
	 * @param key
	 * @return
	 *//*
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

	*//**
	 * 保存一个值
	 * 
	 * @param key
	 * @param value
	 * @return
	 *//*
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
	
	*//**
	 * 实现命令：SET key value EX seconds，设置key-value和超时时间（秒）
	 * 
	 * @param key
	 * @param value
	 * @param timeout
	 *            （以秒为单位）
	 *//*
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

	*//**
	 * 实现命令：expire 设置过期时间，单位秒
	 * 
	 * @param key
	 * @return
	 *//*
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
	
	*//**
	 * 保存一个对象
	 * @param key
	 * @param data
	 *//*
	public void setObject(RedisKey key, Object data) {	
		if(key.getExpire() <= 0) {
			set(key.toString(), JsonUtils.objectToJson(data));	
		}else {
			set(key.toString(), key.getExpire(), JsonUtils.objectToJson(data));
		}
			
	}	
	
	
	*//**
	 * 获取一个对象
	 * @param key
	 * @return 
	 * @return 
	 *//*
	public Object getObject(RedisKey key) {
		String data = get(key.toString());
		if(!StringUtils.isEmpty(data)) {
			 return  JsonUtils.jsonToPojo(data, key.getClass());
		}
		return null;
	}	
	
	*//**
	 * 获取一个List
	 * @param <T>
	 * @param key
	 * @return 
	 * @return 
	 *//*
	public <T> List<T> getList(RedisKey key, Class<T> clazz) {
		String data = get(key.toString());
		if(!StringUtils.isEmpty(data)) {
			 return  JsonUtils.jsonToList(data, clazz);
		}
		return null;
	}
	
	*//**
	 * 获取一个对象，并且让它继续存活一段时间
	 * @param key
	 * @return
	 *//*
	public Object getObjectAndActive(RedisKey key) {		
		Jedis jedis = jedisPool.getResource();
		String data = "";
		Object t = null;
		try {
			data = jedis.get(key.toString());
			jedis.expire(key.toString(), key.getExpire());
			if(!StringUtils.isEmpty(data)) {
				 t =  JsonUtils.jsonToPojo(data, key.getClazz());
			}
		}catch(Exception e) {
			e.printStackTrace();
			return t;
		}finally {
			try {
				jedis.close();
			} catch (Exception e) {
				e.printStackTrace();
				return t;
			}
		}
		
		return t;
	}
	
	*//**
	 * 获取一个对象，并且让它继续存活一段时间
	 * @param key
	 * @return
	 *//*
	@SuppressWarnings("unchecked")
	public <T> List<T> getListAndActive(RedisKey key,  Class<T> clazz) {		
		Jedis jedis = jedisPool.getResource();
		String data = "";
		List<T> t = null;
		try {
			data = jedis.get(key.toString());
			jedis.expire(key.toString(), key.getExpire());
			if(!StringUtils.isEmpty(data)) {
				 t =  (List<T>) JsonUtils.jsonToList(data, key.getClazz());
			}
		}catch(Exception e) {
			e.printStackTrace();
			return t;
		} finally {
			try {
				jedis.close();
			} catch (Exception e) {
				e.printStackTrace();
				return t;
			}
		}
		
		return t;
	}

	*//**
	 * 删除一个对象
	 * @param key
	 *//*
	public void delete(RedisKey key) {
		Jedis jedis = jedisPool.getResource();
		try {
			jedis.del(key.toString());
			
		}catch(Exception e) {
			e.printStackTrace();			
		} finally {
			try {
				jedis.close();
			} catch (Exception e) {
				e.printStackTrace();				
			}
		}
		
	}*/
	
	
}
