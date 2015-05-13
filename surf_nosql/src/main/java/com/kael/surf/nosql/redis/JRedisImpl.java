package com.kael.surf.nosql.redis;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.pool.impl.GenericObjectPool;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

public class JRedisImpl implements JRedis {
	
	SharedJRedisPool sharedJRedisPool;
	
	public String getSharedName() {
		return sharedJRedisPool.getSharedName();
	}
	
	public JRedisImpl(SharedRedisInfo info,GenericObjectPool.Config config) {
		sharedJRedisPool = new SharedJRedisPool(info, config);
	}

	@Override
	public void auth(String pwd) {
	}

	@Override
	public Long append(String key, String value) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.append(key, value);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("append", key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Long append(byte[] key, byte[] value) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.append(key, value);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("append", key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
		
	}

	@Override
	public void bgrewriteaof() {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			jedis.bgrewriteaof();
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("bgrewriteaof", "", null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Long dbsize() {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.dbSize();
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("dbsize", "", null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}
	
	
	@Override
	public String ping() {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.ping();
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("ping", "", null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Long decr(String key) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.decr(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("decr", key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Long decr(byte[] key) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.decr(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("decr", key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Long decrby(String key, int decrment) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.decrBy(key, decrment);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("decrby", key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Long decrby(byte[] key, int decrment) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.decrBy(key, decrment);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("decrby", key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Long del(String... keys) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.del(keys);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("del", Arrays.toString(keys), null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Long del(byte[]... keys) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.del(keys);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("del", Arrays.toString(keys), null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public boolean exists(String key) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.exists(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("exists", key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public boolean exists(byte[] key) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.exists(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("exists", key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Long expire(String key, int seconds) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.expire(key, seconds);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("expire", key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}		
	}

	@Override
	public Long expireAt(String key, long millseconds) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.expireAt(key, millseconds);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("expireAt", key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}	
		
	}

	@Override
	public Long persist(String key) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.persist(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("persist", key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
		
	}

	@Override
	public Long pexpire(String key, long seconds) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.pexpire(key,seconds);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("pexpire", key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Long pexpireAt(String key, long millseconds) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.pexpireAt(key,millseconds);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("pexpireAt", key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
		
	}

	@Override
	public String psetex(String key, long millseconds, String value) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.psetex(key,millseconds,value);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("psetex", key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public long pttl(String key) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.pttl(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("pttl", key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Long expire(byte[] key, int seconds) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.expire(key, seconds);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("expire", key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
		
	}

	@Override
	public Long expireat(byte[] key, long millseconds) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.expireAt(key, millseconds);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("expireAt", key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}	
	}

	@Override
	public Long persist(byte[] key) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.persist(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("persist", key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
		
	}

	@Override
	public Long pexpire(byte[] key, long seconds) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.pexpire(key,seconds);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("pexpire", key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Long pexpireAt(byte[] key, long millseconds) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.pexpireAt(key,millseconds);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("pexpireAt", key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
		
	}

	@Override
	public String psetex(byte[] key, long millseconds, byte[] value) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.psetex(key,millseconds,value);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("psetex", key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
		
	}

	@Override
	public long pttl(byte[] key) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.pttl(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("pttl", key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}
	
	@Override
	public long ttl(String key) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.ttl(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("ttl", key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public long ttl(byte[] key) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.ttl(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("ttl", key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public String flushAll() {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.flushAll();
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("flushAll", "", "",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public String flushDB() {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.flushDB();
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("flushDB", "", "",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public String get(String key) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.get(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("get", key, "",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public byte[] get(byte[] key) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.get(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("get", key, "",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public String set(String key,String value) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.set(key,value);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("set", key, value,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public String set(byte[] key,byte[] value) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.set(key,value);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("set", key, Arrays.toString(value),e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Boolean getbit(String key, long offset) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.getbit(key, offset);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("getbit", key, ""+offset,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public String getrange(String key, int start, int end) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.getrange(key, start,end);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("getrange", key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public String getSet(String key, String value) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.getSet(key, value);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("getSet", key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public byte[] getSet(byte[] key, byte[] value) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.getSet(key, value);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("getSet", key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public String setex(String key, int seconds, String value) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.setex(key,seconds,value);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("setex", key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}		
	}

	@Override
	public Long setnx(String key, String value) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.setnx(key,value);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("setnx", key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}	
	}

	@Override
	public String setex(byte[] key, int seconds, byte[] value) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.setex(key,seconds,value);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("setex", key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}	
	}

	@Override
	public Long setnx(byte[] key, byte[] value) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.setnx(key,value);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("setnx", key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public String randomKey() {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.randomKey();
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("randomKey", "", null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

//	@Override
//	public byte[] randomBytesKey() {
//		return null;
//	}

	@Override
	public Long hdel(String key, String... fields) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.hdel(key,fields);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("hdel", key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public boolean hexist(String key, String field) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.hexists(key, field);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("hexists",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public String hget(String key, String field) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.hget(key, field);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("hget",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Map<String, String> hgetAll(String key) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.hgetAll(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("hgetAll",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Long hincrBy(String key, String field, int increment) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.hincrBy(key, field, increment);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("hincrBy",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Double hincrByFloat(String key, String field, double increment) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.hincrByFloat(key, field, increment);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("hincrByFloat",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Set<String> hkeys(String key) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.hkeys(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("hkeys",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Long hlen(String key) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.hlen(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("hlen",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public List<String> hmget(String key, String... fields) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.hmget(key, fields);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("hmget",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public String hmset(String key, Map<String, String> kvstr) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.hmset(key, kvstr);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("hmset",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public long hset(String key, String field, String value) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.hset(key, field,value);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("hset",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Long hsetnx(String key, String field, String value) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.hsetnx(key, field,value);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("hsetnx",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

//	@Override
//	public long hstrlen(String key, String field) {
//		Jedis jedis = null;
//		boolean broken = false;
//		try {
//			jedis = sharedJRedisPool.getResource();
//			return jedis.hs
//		} catch (Exception e) {
//			e.printStackTrace();
//			broken = true;
//			throw new KRedisException("hsetnx",key, null,e);
//		}finally{
//			if(broken){
//				sharedJRedisPool.returnBrokenResource(jedis);
//			}else{
//				sharedJRedisPool.returnResource(jedis);
//			}
//		}
//	}

	@Override
	public List<String> hvals(String key) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.hvals(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("hvals",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Long hdel(byte[] key, byte[]... fields) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.hdel(key,fields);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("hdel", key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
		
	}

	@Override
	public boolean hexist(byte[] key, byte[] field) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.hexists(key, field);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("hexists",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public byte[] hget(byte[] key, byte[] field) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.hget(key, field);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("hget",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Map<byte[], byte[]> hgetAll(byte[] key) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.hgetAll(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("hgetAll",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Long hincrBy(byte[] key, byte[] field, int increment) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.hincrBy(key, field, increment);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("hincrBy",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public double hincrByFloat(byte[] key, byte[] field, double increment) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.hincrByFloat(key, field, increment);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("hincrByFloat",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Set<byte[]> hkeys(byte[] key) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.hkeys(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("hkeys",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Long hlen(byte[] key) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.hlen(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("hlen",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public List<byte[]> hmget(byte[] key, byte[]... fields) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.hmget(key, fields);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("hmget",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public String hmset(byte[] key, Map<byte[], byte[]> kvBytes) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.hmset(key, kvBytes);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("hmset",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public long hset(byte[] key, byte[] field, byte[] value) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.hset(key, field,value);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("hset",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Long hsetnx(byte[] key, byte[] field, byte[] value) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.hsetnx(key, field,value);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("hsetnx",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public List<byte[]> hvals(byte[] key) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.hvals(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("hvals",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public long incr(String key) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.incr(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("incr",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public long incr(byte[] key) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.incr(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("incr",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public long incrBy(String key, int increment) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.incrBy(key,increment);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("incrBy",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public long incrBy(byte[] key, int increment) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.incrBy(key,increment);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("incrBy",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Double incrByFloat(String key, double increment) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.incrByFloat(key,increment);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("incrByFloat",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Double incrByFloat(byte[] key, double increment) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.incrByFloat(key,increment);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("incrByFloat",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Set<String> keys(String pattern) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.keys(pattern);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("keys",pattern, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Set<byte[]> keys(byte[] pattern) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.keys(pattern);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("keys",pattern, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public long lastsave() {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.lastsave();
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("lastsave","", null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public String lindex(String key, long index) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.lindex(key,index);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("lindex",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Long llen(String key) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.llen(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("llen",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public String lpop(String key) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.lpop(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("lpop",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Long lpush(String key, String... values) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.lpush(key, values);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("lpush",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
		
	}

	@Override
	public Long lpushx(String key, String... values) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.lpushx(key, values);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("lpushx",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
		
	}

	@Override
	public List<String> lrange(String key, long start, long end) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.lrange(key, start, end);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("lrange",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Long lrem(String key, int count, String value) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.lrem(key, count, value);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("lrem",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
		
	}

	@Override
	public String lset(String key, int index, String value) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.lset(key, index, value);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("lset",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public String ltrim(String key, long start, long end) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.ltrim(key, start, end);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("ltrim",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public String rpop(String key) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.rpop(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("rpop",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Long rpush(String key, String... strings) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.rpush(key, strings);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("rpush",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Long rpushx(String key, String value) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.rpushx(key, value);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("rpushx",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
		
	}

	@Override
	public String rpoplpush(String srcKey, String dstKey) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.rpoplpush(srcKey, dstKey);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("rpoplpush",srcKey, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}


	@Override
	public byte[] lindex(byte[] key, long index) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.lindex(key, index);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("lindex",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Long llen(byte[] key) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.llen(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("llen",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public byte[] lpop(byte[] key) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.lpop(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("lpop",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Long lpush(byte[] key, byte[]... values) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.lpush(key, values);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("lpush",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
		
	}

	@Override
	public Long lpushx(byte[] key, byte[]... values) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.lpushx(key, values);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("lpushx",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public List<byte[]> lrange(byte[] key, long start, long end) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.lrange(key, start, end);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("lrange",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public long lrem(byte[] key, int count, byte[] value) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.lrem(key, count, value);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("lrem",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public String lset(byte[] key, int index, byte[] value) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.lset(key, index, value);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("lset",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public String ltrim(byte[] key, long start, long end) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.ltrim(key, start, end);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("ltrim",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public byte[] rpop(byte[] key) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.rpop(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("rpop",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Long rpush(byte[] key, byte[]... bytes) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.rpush(key, bytes);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("rpush",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Long rpushx(byte[] key, byte[] value) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.rpushx(key, value);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("rpushx",key, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
		
	}

	@Override
	public byte[] rpoplpush(byte[] srcKey, byte[] dstKey) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.rpoplpush(srcKey, dstKey);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("rpoplpush",srcKey, null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}
	
	@Override
	public List<String> mget(String... keys) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.mget(keys);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("mget",Arrays.toString(keys), null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public String mset(String... keysvalues) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.mset(keysvalues);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("mset",Arrays.toString(keysvalues), null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Long msetnx(String... keysvalues) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.msetnx(keysvalues);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("msetnx",Arrays.toString(keysvalues), null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public List<byte[]> mget(byte[]... keys) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.mget(keys);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("mget",Arrays.toString(keys), null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}
	

	@Override
	public String mset(byte[]... keysvalues) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.mset(keysvalues);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("mset",Arrays.toString(keysvalues), null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Long msetnx(byte[]... msetnx) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.msetnx(msetnx);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("msetnx",Arrays.toString(msetnx), null,e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public long sadd(String key, String... members) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.sadd(key, members);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("msetnx",key,Arrays.toString(members),e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public long scard(String key) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.scard(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("msetnx",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public boolean sismember(String key, String member) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.sismember(key, member);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("sismember",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Set<String> smembers(String key) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.smembers(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("smembers",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public String spop(String key) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.spop(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("spop",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}
	
	@Override
	public Set<String> spop(String key, int count) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.spop(key, count);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("spop",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public String srandmember(String key) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.srandmember(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("srandmember",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}
	
	@Override
	public List<String> srandmember(String key, int count) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.srandmember(key,count);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("srandmember",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public long srem(String key, String... values) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.srem(key,values);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("srem",key,Arrays.toString(values),e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public long sadd(byte[] key, byte[]... members) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.sadd(key, members);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("msetnx",key,Arrays.toString(members),e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public long scard(byte[] key) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.scard(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("msetnx",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public boolean sismember(byte[] key, byte[] member) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.sismember(key, member);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("sismember",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Set<byte[]> smembers(byte[] key) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.smembers(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("smembers",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public byte[] spop(byte[] key) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.spop(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("spop",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}
	
	@Override
	public byte[] srandmember(byte[] key) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.srandmember(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("srandmember",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}
	
	@Override
	public Set<byte[]> spop(byte[] key, int count) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.spop(key, count);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("spop",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public List<byte[]> srandmember(byte[] key, int count) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.srandmember(key,count);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("srandmember",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public long srem(byte[] key, byte[]... values) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.srem(key,values);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("srem",key,Arrays.toString(values),e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public long zadd(String key, double score, String member) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zadd(key,score,member);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zadd",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public long zadd(String key, Map<String, Double> scoreMembers) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zadd(key, scoreMembers);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zadd",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}
	
	@Override
	public long zcard(String key) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zcard(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zcard",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public long zcount(String key, double minScore, double maxScore) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zcount(key, minScore, maxScore);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zcount",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public double zincrby(String key, double increment, String member) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zincrby(key, increment, member);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zincrby",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
		
	}

	@Override
	public Set<String> zrange(String key, long start, long end) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zrange(key, start, end);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zrange",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}
	
	@Override
	public Set<Tuple> zrangeWithScores(String key, long start, long end) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zrangeWithScores(key, start, end);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zrangeWithScores",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}
	
	@Override
	public Set<String> zrangeByLex(String key, String min, String max) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zrangeByLex(key, min, max);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zrangeByLex",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Set<String> zrangeByLex(String key, String min, String max, int offset, int count) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zrangeByLex(key, min, max, offset, count);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zrangeByLex",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Set<String> zrevrangeByLex(String key, String max, String min) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zrevrangeByLex(key, max, min);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zrevrangeByLex",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Set<String> zrevrangeByLex(String key, String max, String min, int offset, int count) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zrevrangeByLex(key, max, min, offset, count);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zrevrangeByLex",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Set<String> zrangeByScore(String key, double min, double max) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zrangeByScore(key, min, max);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zrangeByScore",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}
	
	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zrangeByScoreWithScores(key, min, max);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zrangeByScoreWithScores",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Long zrank(String key, String member) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zrank(key, member);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zrank",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
		
	}

	@Override
	public Long zrem(String key, String member) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zrem(key, member);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zrem",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Long zremrangeByLex(String key, String min, String max) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zremrangeByLex(key, min, max);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zremrangeByLex",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
		
	}

	@Override
	public Long zremrangeByRank(String key, int start, int end) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zremrangeByRank(key, start, end);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zremrangeByRank",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Long zremrangebyscore(String key, double start, double end) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zremrangeByScore(key, start, end);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zremrangeByScore",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Set<String> zrevrange(String key, int start, int end) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zrevrange(key, start, end);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zrevrange",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Set<Tuple> zrevrangeWithScores(String key, int start, int end) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zrevrangeWithScores(key, start, end);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zrevrangeWithScores",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	@Override
	public Long zrevrank(String key, String member) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zrevrank(key,member);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zrevrank",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
		
	}

	@Override
	public double zscore(String key, String member) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zscore(key, member);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zscore",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}
	
	@Override
	public long zadd(byte[] key, double score, byte[] member) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zadd(key,score,member);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zadd",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}
	
	@Override
	public long zadd(byte[] key, Map<byte[], Double> scoreMembers) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zadd(key, scoreMembers);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zadd",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}
	
	@Override
	public long zcard(byte[] key) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zcard(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zcard",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}
	
	@Override
	public long zcount(byte[] key, double minScore, double maxScore) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zcount(key, minScore, maxScore);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zcount",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}
	
	@Override
	public double zincrby(byte[] key, double increment, byte[] member) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zincrby(key, increment, member);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zincrby",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
		
	}
	
	@Override
	public Set<byte[]> zrange(byte[] key, long start, long end) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zrange(key, start, end);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zrange",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}
	
	@Override
	public Set<Tuple> zrangeWithScores(byte[] key, long start, long end) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zrangeWithScores(key, start, end);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zrangeWithScores",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}
	
	@Override
	public Set<byte[]> zrangeByLex(byte[] key, byte[] min, byte[] max) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zrangeByLex(key, min, max);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zrangeByLex",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}
	
	@Override
	public Set<byte[]> zrangeByLex(byte[] key, byte[] min, byte[] max, int offset, int count) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zrangeByLex(key, min, max, offset, count);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zrangeByLex",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}
	
	@Override
	public Set<byte[]> zrevrangeByLex(byte[] key, byte[] max, byte[] min) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zrevrangeByLex(key, max, min);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zrevrangeByLex",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}
	
	@Override
	public Set<byte[]> zrevrangeByLex(byte[] key, byte[] max, byte[] min, int offset, int count) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zrevrangeByLex(key, max, min, offset, count);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zrevrangeByLex",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}
	
	@Override
	public Set<byte[]> zrangeByScore(byte[] key, double min, double max) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zrangeByScore(key, min, max);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zrangeByScore",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}
	
	@Override
	public Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zrangeByScoreWithScores(key, min, max);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zrangeByScoreWithScores",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}
	
	@Override
	public Long zrank(byte[] key, byte[] member) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zrank(key, member);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zrank",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
		
	}
	
	@Override
	public Long zrem(byte[] key, byte[] member) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zrem(key, member);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zrem",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}
	
	@Override
	public Long zremrangeByLex(byte[] key, byte[] min, byte[] max) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zremrangeByLex(key, min, max);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zremrangeByLex",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
		
	}
	
	@Override
	public Long zremrangeByRank(byte[] key, int start, int end) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zremrangeByRank(key, start, end);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zremrangeByRank",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}
	
	@Override
	public Long zremrangeByScore(byte[] key, double start, double end) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zremrangeByScore(key, start, end);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zremrangeByScore",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}
	
	@Override
	public Set<byte[]> zrevrange(byte[] key, int start, int end) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zrevrange(key, start, end);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zrevrange",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}
	
	@Override
	public Set<Tuple> zrevrangeWithScores(byte[] key, int start, int end) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zrevrangeWithScores(key, start, end);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zrevrangeWithScores",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}
	
	@Override
	public Long zrevrank(byte[] key, byte[] member) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zrevrank(key,member);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zrevrank",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
		
	}
	
	@Override
	public double zscore(byte[] key, byte[] member) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			return jedis.zscore(key, member);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
			throw new KRedisException("zscore",key,"",e);
		}finally{
			closeJedis(jedis, broken);
		}
	}

	private Jedis getJedis() {
		return sharedJRedisPool.getResource();
	}

	private void closeJedis(Jedis jedis, boolean broken) {
		if(broken){
			sharedJRedisPool.returnBrokenResource(jedis);
		}else{
			sharedJRedisPool.returnResource(jedis);
		}
	}
	
	

	@Override
	public void muti(String key) {
		
		
	}

	@Override
	public void watch(String key) {
		
		
	}

	@Override
	public void exec() {
		
		
	}

	@Override
	public void unwatch() {
		
		
	}

}
