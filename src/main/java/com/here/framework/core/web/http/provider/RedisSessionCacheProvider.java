package com.here.framework.core.web.http.provider;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

import com.here.framework.core.cache.HObjectSerializable;
import com.here.framework.core.redis.RedisPool;
import com.here.framework.core.web.http.HSessionCacheProvider;
import com.here.framework.log.HLogger;
/**
 * redis缓存session实现
 * @author koujp
 *
 */
public class RedisSessionCacheProvider implements HSessionCacheProvider {
	private static final long serialVersionUID = -8343390980166888894L;
	private static final Charset cacheEncodeCharset=Charset.forName("iso-8859-1");
	private Map<String,Object> cache=new HashMap<String, Object>();
	private int maxage=60*60;
	private String sessionId;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@Override
	public long getMaxage() {
		return maxage;
	}

	@Override
	public void setMaxage(int maxage) {
		this.maxage=maxage;
		
	}

	@Override
	public void updateMaxage() {
		Jedis jedis=RedisPool.getSessionJedis();
		try{
			if(null!=jedis){
				int age=(int)maxage;
				jedis.expire(sessionId, age);
			}
		}finally{
			RedisPool.returnSessionJedis(jedis);
		}	
		
	}

	@Override
	public Object setAttribute(String name, Object value) {
		Jedis jedis=RedisPool.getSessionJedis();
		try{
			if(null!=jedis){
				if(null==value || null==name){
					return value;
				}
				byte[] bytes=HObjectSerializable.serializable(value);
				String val=new String(bytes, cacheEncodeCharset);
				
				jedis.hset(sessionId, name, val);
				
				return value;
			}else{
				return cache.put(name, value);
			}
		}catch(Exception e){
			HLogger.getInstance(this.getClass()).error(e);
		}finally{
			RedisPool.returnSessionJedis(jedis);
		}
		return null;
	}

	@Override
	public Object getAttribute(String name) {
		Jedis jedis=RedisPool.getSessionJedis();
		try{
			if(null!=jedis){
				
				String val=jedis.hget(sessionId, name);
				if(null==val){
					return val;
				}
				return HObjectSerializable.deserializable(val.getBytes(cacheEncodeCharset));
			}else{
				return cache.get(name);
			}
		}catch(Exception e){
			HLogger.getInstance(this.getClass()).error(e);
		}finally{
			RedisPool.returnSessionJedis(jedis);
		}
		return null;
	}

	@Override
	public Object removeAttribute(String name) {
		Jedis jedis=RedisPool.getSessionJedis();
		try{
			if(null!=jedis){
				if(null==name){
					return null;
				}
				String value=jedis.hget(sessionId,name);
				jedis.hdel(sessionId, name);
				
				return value;
			}else{
				return cache.remove(name);
			}
		}finally{
			RedisPool.returnSessionJedis(jedis);
		}
	}

	@Override
	public void invalidate() {
		Jedis jedis=RedisPool.getSessionJedis();
		try{
			if(null!=jedis){
				jedis.del(sessionId);
			}else{
				cache.clear();
			}
		}finally{
			RedisPool.returnSessionJedis(jedis);
		}
	}

	@Override
	public List<String> attributeNames() {
		Jedis jedis=RedisPool.getSessionJedis();
		List<String> attrNames=new ArrayList<String>();
		try{
			if(null!=jedis){
				Set<String> keys=jedis.hkeys(sessionId);
				if(null!=keys){
					attrNames.addAll(keys);
				}
			}else{
				attrNames.addAll(cache.keySet());
			}
		}finally{
			RedisPool.returnSessionJedis(jedis);
		}
		return attrNames;
	}

}
