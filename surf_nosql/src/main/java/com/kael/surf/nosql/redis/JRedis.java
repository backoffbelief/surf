package com.kael.surf.nosql.redis;

import java.util.List;
import java.util.Map;

public interface JRedis {

	//connection
	void auth(String pwd);
	
	//string
	void append(String key,String value);
	void append(byte[] key,String value);
	
	void bgrewriteaof();
	
	int dbsize();
	
	long decr(String key);
	
	long decr(byte[] key);
	
	long decrby(String key, int decrment);

	long decrby(byte[] key, int decrment);
	
	void del(String key);
	
	void del(byte[] key);
	
	boolean exists(String key);

	boolean exists(byte[] key);
	
	void expire(String key, int seconds);
	
	void expireat(String key,long millseconds);
	
	/**
	 * remove the expiration from a key
	 * @param key
	 */
	void persist(String key);
	
	void pexpire(String key,int seconds);
	
	void pexpireat(String key, long millseconds);
	
	void psetex(String key,long millseconds,String value);
	
	long pttl(String key);

	void expire(byte[] key, int seconds);
	
	void expireat(byte[] key,long millseconds);
	
	/**
	 * remove the expiration from a key
	 * @param key
	 */
	void persist(byte[] key);
	
	void pexpire(byte[] key,int seconds);
	
	void pexpireat(byte[] key, long millseconds);
	
	void psetex(byte[] key,long millseconds,byte[] value);
	
	long pttl(byte[] key);
	
	
	/**
	 * remove all keys from all db
	 */
	void flushall();

	/**
	 * remove all keys from current db
	 */
	void flushdb();
	
	String get(String key);
	
	byte[] get(byte[] key);
	
	String getbit(String key, int offset);
	
	String getrange(String key, int start,int end);
	
	String getset(String key,String value);

	byte[] getset(byte[] key,byte[] value);
	
	String randomStrKey();

	byte[] randomBytesKey();
	
	//hashs
	
	void hdel(String key,String... fields);
	
	boolean hexist(String key, String field);
	
	String hget(String key,String field);
	
	Map<String, String> hgetall(String key);
	
	Long hincrby(String key,String field,int increment);
	
	float hincrbyfloat(String key,String field, float increment);
	
	List<String> hkeys(String key);
	
	Long hlen(String key);
	
	Map<String, String> hmget(String key, String... fields);
	
	long hmset(String key,Map<String, String> kvstr);
	
	long hset(String key,String field,String value);
	
	boolean hsetinx(String key,String field,String value);
	
	long hstrlen(String key,String field);
	
	List<String> hvals(String key);
	
	
    void hdel(byte[] key,byte[]... fields);
	
	boolean hexist(byte[] key, byte[] field);
	
	byte[] hget(byte[] key,byte[] field);
	
	Map<byte[], byte[]> hgetall(byte[] key);
	
	Long hincrby(byte[] key,byte[] field,int increment);
	
	float hincrbyfloat(byte[] key,byte[] field, float increment);
	
	List<byte[]> hkeys(byte[] key);
	
	Long hlen(byte[] key);
	
	Map<byte[], byte[]> hmget(byte[] key, byte[]... fields);
	
	long hmset(byte[] key,Map<byte[], byte[]>  kvBytes);
	
	long hset(byte[] key,byte[] field,byte[] value);
	
	boolean hsetinx(byte[] key,byte[] field,byte[] value);
	
	//long hstrlen(byte[] key,byte[] field);
	
	List<byte[]> hvals(byte[] key);
	/**
	static interface KV<T>{
		T getKey();
		T getValue();
	}
	
	static class KVStr implements KV<String>{
		final String key;
		final String value;

		public KVStr(String key, String value) {
			super();
			this.key = key;
			this.value = value;
		}

		@Override
		public String getKey() {
			return key;
		}

		@Override
		public String getValue() {
			return value;
		}
	}
	
	static class KVBytes implements KV<byte[]>{
		final byte[] key;
		final byte[] value;

		public KVBytes(byte[] key, byte[] value) {
			super();
			this.key = key;
			this.value = value;
		}

		@Override
		public byte[] getKey() {
			return key;
		}

		@Override
		public byte[] getValue() {
			return value;
		}
	}
	*/
	
	long incr(String key);
	
	long incr(byte[] key);
	
	long incrby(String key,int increment);

	long incrby(byte[] key,int increment);
	
	float incrbyfloat(String key,float increment);
	
	float incrbyfloat(byte[] key,float increment);

	List<String> keys(String pattern);

	List<byte[]> keys(byte[] pattern);
	/**
	 * get unix time stamp of last successful save to disk
	 * @return
	 */
	long lastsave();
	
	
	//list
	String lindex(String key,int index);

	int llen(String key);
	
	long lpop(String key);
	
	void lpush(String key,String... values);
	
	/**
	 * prepend a value to a list  only if this list exists
	 * @param key
	 * @param value
	 */
	void lpushhx(String key,String value);
	
	List<String> lrange(String key, int start,int stop);
	
	void lrem(String key,int count,String value);
	
	long lset(String key,int index, String value);
	
	List<String> ltrim(String key,int start,int stop);
	
	String rpop(String key);
	//String rpoplpush(String key);
	String rpushStrs(Map<String, String> kvStrs);
	
	void rpushx(String key,String value);
	
	
	byte[] lindex(byte[] key,int index);
	
	int llen(byte[] key);
	
	long lpop(byte[] key);
	
	void lpush(byte[] key,byte[]... values);
	
	/**
	 * prepend a value to a list  only if this list exists
	 * @param key
	 * @param value
	 */
	boolean lpushhx(byte[] key,byte[] value);
	
	List<byte[]> lrange(byte[] key, int start,int stop);
	
	long lrem(byte[] key,int count,byte[] value);
	
	long lset(byte[] key,int index, byte[] value);
	
	List<byte[]> ltrim(byte[] key,int start,int stop);
	
	byte[] rpop(byte[] key);
	//String rpoplpush(String key);
	byte[] rpushBytes(Map<byte[], byte[]> kvBytes);
	
	void rpushx(byte[] key,byte[] value);
	//
    
//	void migrate(String host,int port,String key)
	
	Map<String, String> mget(String... keys);
	
	long mset(Map<String, String> kvstrs);
	
	boolean msetnx(Map<String, String> kvstrs);

	
	Map<byte[], byte[]> mget(byte[]... keys);
	
	long msetBytes(Map<byte[], byte[]> kvBytes);
	
	boolean msetnxBytes(Map<byte[], byte[]> kvBytes);
	
	
	
	
	//事务
	void muti(String key);
}
