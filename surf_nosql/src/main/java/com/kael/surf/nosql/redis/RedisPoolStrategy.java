package com.kael.surf.nosql.redis;

public interface RedisPoolStrategy {

	
	SharedJRedisPool select(byte[] key);
	
	SharedJRedisPool select(String key);
}
