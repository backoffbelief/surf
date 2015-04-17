package com.kael.surf.nosql.redis;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool.Config;

public class JRedisFactory {

	private static Config initConfig() {
		Config config = new Config();
		config.timeBetweenEvictionRunsMillis = 60 * 1000;
		config.maxActive = 100;
		config.maxIdle = 50;
		config.maxWait = GenericObjectPool.WHEN_EXHAUSTED_GROW;
		config.testWhileIdle = true;
		return config;
	}
}
