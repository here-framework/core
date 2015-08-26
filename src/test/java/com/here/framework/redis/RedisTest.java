package com.here.framework.redis;

import com.here.framework.core.redis.RedisPool;

import redis.clients.jedis.Jedis;

public class RedisTest {

	public static void main(String[] args) {
		Jedis jedis = RedisPool.getJedis("redisConfig_business");
		jedis.set("Demo", "DEMO");
		jedis.lpush("list", "list1");
		RedisPool.returnJedis("redisConfig_business",jedis);
		
		Jedis jedis2 = RedisPool.getJedis("redisConfig_session");
		jedis2.get("Demo");
		System.out.print(jedis2.get("Demo"));
	}

}
