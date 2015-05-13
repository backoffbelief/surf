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

	public static JRedis createJredis(String masters, String slaves) {

		Config initConfig = initConfig();
		String[] split = masters.split(",");
		JRedisProxy jRedisProxy = new JRedisProxy(split.length);
		for (String master : split) {
			String[] tmp = master.split(":");
			JRedisImpl jRedisImpl = new JRedisImpl(new SharedRedisInfo(tmp[0],
					tmp[1], tmp[1]), initConfig);
			jRedisProxy.addJRedis(jRedisImpl);
		}
		return jRedisProxy;
	}

	public static JRedis createSignleJredis(String master) {
		Config initConfig = initConfig();
		String[] tmp = master.split(":");
		JRedisImpl jRedisImpl = new JRedisImpl(new SharedRedisInfo(tmp[0],
				tmp[1], tmp[1]), initConfig);
		return jRedisImpl;
	}
}
