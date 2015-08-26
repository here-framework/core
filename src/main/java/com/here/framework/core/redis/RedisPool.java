package com.here.framework.core.redis;

import com.here.framework.bean.InstanceManager;
import com.here.framework.core.config.RedisConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool 
{
	public final static String REDIS_BUSINESS = "redisConfig_business";
	public final static String REDIS_SESSION = "redisConfig_session";
	
	private RedisConfig redisConfig = null;
	private JedisPool jedisPool = null;
	private Object lockObject = new Object();
	
	public RedisPool(RedisConfig redisConfig)
	{
		this.setRedisConfig(redisConfig);
	}
	private void initJedisPool() 
	{
		RedisConfig redisConfig = this.getRedisConfig();
		
		JedisPoolConfig config = new JedisPoolConfig();

		config.setMaxTotal(redisConfig.getMaxTotal());
		config.setMaxIdle(redisConfig.getMaxIdle());
		config.setMinIdle(redisConfig.getMinIdle());
		config.setTestOnBorrow(redisConfig.isTestOnBorrow());
		
		this.jedisPool = new JedisPool(config, redisConfig.getHost(), redisConfig.getPort(),redisConfig.getTimeOut(),redisConfig.getPassword(),0);
	}
	public Jedis getJedis()
	{
		if (this.jedisPool == null) 
		{
			synchronized (lockObject) 
			{
				this.initJedisPool();
			}
		}
		if (this.jedisPool != null) 
		{
			return this.jedisPool.getResource();
		}
		
		return null;
	}
	public void returnJedis(Jedis jedis)
	{
		if (this.jedisPool == null) 
		{
			synchronized (lockObject) 
			{
				this.initJedisPool();
			}
		}
		if (this.jedisPool != null) 
		{
			this.jedisPool.returnResource(jedis);
		}
	}
	
	/**
     * 获取普通业务Jedis
     * 
     * @return
     */
	public static Jedis getBusinessJedis()
	{
		return getJedis(REDIS_BUSINESS);
	}
	/**
     * 归还普通业务Jedis
     * 
     * @return
     */
	public static void returnBusinessJedis(Jedis jedis)
	{
		returnJedis(REDIS_BUSINESS,jedis);
	}
	/**
     * 获取会话Jedis
     * 
     * @return
     */
	public static Jedis getSessionJedis()
	{
		return getJedis(REDIS_SESSION);
	}
	/**
     * 归还普通业务Jedis
     * 
     * @return
     */
	public static void returnSessionJedis(Jedis jedis)
	{
		returnJedis(REDIS_SESSION,jedis);
	}
	/**
     * 通过名称获取jedis
     * 
     * @return
     */
	public static Jedis getJedis(String name)
	{
		RedisPool pool = InstanceManager.getInstance(name, RedisPool.class);
		
		if (pool != null) 
		{
			return pool.getJedis();
		}
		return null;
	}
    /**
     * 归还Jedis
     * 
     * @param jedis
     */
    public static void returnJedis(String name,Jedis jedis) 
    {
    	RedisPool pool = InstanceManager.getInstance(name, RedisPool.class);
		
		if (pool != null) 
		{
			pool.returnJedis(jedis);
		}
    }
	public RedisConfig getRedisConfig() {
		return redisConfig;
	}
	public void setRedisConfig(RedisConfig redisConfig) {
		this.redisConfig = redisConfig;
	}
	public JedisPool getJedisPool() {
		return jedisPool;
	}
	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}
}
