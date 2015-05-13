package com.kael.surf.nosql.memcached;

import java.io.IOException;
import java.util.Properties;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

public class MemCachedApp {
	public static void main(String[] args) {
		
		Properties p = new Properties();
		
		try {
			p.load(ClassLoader.getSystemResourceAsStream(""));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		SockIOPool pool = SockIOPool.getInstance();
		pool.setServers(p.getProperty("servers").split(","));
		pool.setFailover(true);
		pool.setInitConn(10);
		pool.setMinConn(5);
		pool.setMaxConn(250);
		pool.setMaintSleep(30);
		pool.setNagle(false);
		pool.setSocketTO(3000);
		pool.setAliveCheck(true);
		pool.initialize();
		
		MemCachedClient client = new MemCachedClient();
		String key = "";
		Object value = null;
		
		client.incr(key);
		
		client.set(key, value);
		client.get(key);
		client.cas(key, value, client.addOrIncr(key));
		client.add(key, value);
		client.append(key, value);
		
	}
}
