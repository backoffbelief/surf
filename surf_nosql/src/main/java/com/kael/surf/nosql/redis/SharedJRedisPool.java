package com.kael.surf.nosql.redis;

import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;

import redis.clients.jedis.Jedis;

public class SharedJRedisPool {

	
	final class SharedJRedisFactory extends BasePoolableObjectFactory<Jedis>{

		private SharedRedisInfo info;
		
		public SharedJRedisFactory(SharedRedisInfo info) {
			super();
			this.info = info;
		}

		@Override
		public Jedis makeObject() throws Exception {
			return new Jedis(info.getMasterHost(), info.getMasterPort(), info.getTimeout());
		}

		@Override
		public void destroyObject(Jedis obj) throws Exception {
			if(obj != null){
				try{
					obj.quit();
					obj.disconnect();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}

		@Override
		public boolean validateObject(Jedis obj) {
			try {
				return obj != null  && "PONG".equalsIgnoreCase(obj.ping());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}
	}
	
	private final GenericObjectPool<Jedis> internalPool ;
	
	private final SharedRedisInfo info;

	public SharedJRedisPool(SharedRedisInfo info,GenericObjectPool.Config config) {
		super();
		this.info = info;
		internalPool = new GenericObjectPool<Jedis>(new SharedJRedisFactory(info), config);
	}
	
	
	public Jedis getResource(){
		try {
			return internalPool.borrowObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getSharedName(){
		return info.getSharedName();
	}
	
	public void returnResource(final Jedis jedis){
		try {
			this.internalPool.returnObject(jedis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void returnBrokenResource(final Jedis jedis){
		try {
			this.internalPool.invalidateObject(jedis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void destory(){
		try {
			this.internalPool.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getNumActive(){
        return internalPool.getNumActive();
    }
    
    public int getNumIdle(){
        return internalPool.getNumIdle();
    }
    
}
