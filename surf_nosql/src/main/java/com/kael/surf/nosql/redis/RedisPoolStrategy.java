package com.kael.surf.nosql.redis;

public interface RedisPoolStrategy {

	
	JRedis select(byte[] key);
	
	JRedis select(String key);
}
