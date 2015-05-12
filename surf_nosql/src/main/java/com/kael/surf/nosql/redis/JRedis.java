package com.kael.surf.nosql.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Tuple;

public interface JRedis {

	//connection
	void auth(String pwd);
	
	void bgrewriteaof();
	
	Long dbsize();
	
	String ping();
	
	/**
	 * get unix time stamp of last successful save to disk
	 * @return
	 */
	long lastsave();
	
	//事务
	void muti(String key);
	
	void watch(String key);
	
	void exec();
	
	void unwatch();
		
	
	//string
	Long append(String key,String value);
	
	Long append(byte[] key,byte[] value);
	
	Long decr(String key);
	
	Long decr(byte[] key);
	
	Long decrby(String key, int decrment);

	Long decrby(byte[] key, int decrment);
	
	/**
	 * jRedisProxy 不能使用
	 * @param keys
	 * @return
	 */
	Long del(String... keys);
	
	/**
	 * jRedisProxy 不能使用
	 * @param keys
	 * @return
	 */
	Long del(byte[]... keys);
	
	boolean exists(String key);

	boolean exists(byte[] key);
	
	Long expire(String key, int seconds);
	
	Long expireAt(String key,long millseconds);
	
	/**
	 * remove the expiration from a key
	 * @param key
	 */
	Long persist(String key);
	
	Long pexpire(String key,long seconds);
	
	Long pexpireAt(String key, long millseconds);
	
	String psetex(String key,long millseconds,String value);
	
	long pttl(String key);

	long ttl(String key);
	
	Long expire(byte[] key, int seconds);
	
	Long expireat(byte[] key,long millseconds);
	
	/**
	 * remove the expiration from a key
	 * @param key
	 */
	Long persist(byte[] key);
	
	Long pexpire(byte[] key,long seconds);
	
	Long pexpireAt(byte[] key, long millseconds);
	
	String psetex(byte[] key,long millseconds,byte[] value);
	
	long pttl(byte[] key);
	
	long ttl(byte[] key);
	
	/**jRedisProxy 不能使用
	 * remove all keys from all db
	 */
	String flushAll();

	/**jRedisProxy 不能使用
	 * remove all keys from current db
	 */
	String flushDB();
	
	String get(String key);
	
	byte[] get(byte[] key);
	
    String set(String key,String value);
	
    String set(byte[] key,byte[] value);
	
	Boolean getbit(String key, long offset);
	
	String getrange(String key, int start,int end);
	
	String getSet(String key,String value);

	byte[] getSet(byte[] key,byte[] value);
	
    String setex(String key,int seconds,String value);
    
    Long setnx(String key,String value);
	
    String setex(byte[] key,int seconds,byte[] value);
    
    Long setnx(byte[] key,byte[] value);
    
    
	String randomKey();

//	byte[] randomBytesKey();
	
	//hashs
	
	Long hdel(String key,String... fields);
	
	boolean hexist(String key, String field);
	
	String hget(String key,String field);
	
	Map<String, String> hgetAll(String key);
	
	Long hincrBy(String key,String field,int increment);
	
	Double hincrByFloat(String key,String field, double increment);
	
	Set<String> hkeys(String key);
	
	Long hlen(String key);
	
	List<String> hmget(String key, String... fields);
	
	String hmset(String key,Map<String, String> kvstr);
	
	long hset(String key,String field,String value);
	
	Long hsetnx(String key,String field,String value);
	
//	long hstrlen(String key,String field);
	
	List<String> hvals(String key);
	
	
    Long hdel(byte[] key,byte[]... fields);
	
	boolean hexist(byte[] key, byte[] field);
	
	byte[] hget(byte[] key,byte[] field);
	
	Map<byte[], byte[]> hgetAll(byte[] key);
	
	Long hincrBy(byte[] key,byte[] field,int increment);
	
	double hincrByFloat(byte[] key,byte[] field, double increment);
	
	Set<byte[]> hkeys(byte[] key);
	
	Long hlen(byte[] key);
	
	List<byte[]> hmget(byte[] key, byte[]... fields);
	
	String hmset(byte[] key,Map<byte[], byte[]>  kvBytes);
	
	long hset(byte[] key,byte[] field,byte[] value);
	
	Long hsetnx(byte[] key,byte[] field,byte[] value);
	
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
	
	long incrBy(String key,int increment);

	long incrBy(byte[] key,int increment);
	
	Double incrByFloat(String key,double increment);
	
	Double incrByFloat(byte[] key,double increment);

	/**
	 * jRedisProxy 不能使用
	 * @param pattern
	 * @return
	 */
	Set<String> keys(String pattern);

	/**
	 * jRedisProxy 不能使用
	 * @param pattern
	 * @return
	 */
	Set<byte[]> keys(byte[] pattern);
	
	List<String> mget(String... keys);
	/**
	 * jRedisProxy 不能使用
	 * @param keysvalues
	 * @return
	 */
	String mset(String... keysvalues);
	/**
	 * jRedisProxy 不能使用
	 * @param msetnx
	 * @return
	 */
	Long msetnx(String... msetnx);

	
	List<byte[]> mget(byte[]... keys);
	/**
	 * jRedisProxy 不能使用
	 * @param keysvalues
	 * @return
	 */
	String mset(byte[]... keysvalues);
	/**
	 * jRedisProxy 不能使用
	 * @param msetnx
	 * @return
	 */
	Long msetnx(byte[]... msetnx);
	
	//list
	String lindex(String key,long index);

	Long llen(String key);
	
	String lpop(String key);
	
	Long lpush(String key,String... values);
	
	/**
	 * prepend a value to a list  only if this list exists
	 * @param key
	 * @param value
	 */
	Long lpushx(String key,String... string);
	
	List<String> lrange(String key, long start,long end);
	
	Long lrem(String key,int count,String value);
	
	String lset(String key,int index, String value);
	
	String ltrim(String key,long start,long end);
	
	String rpop(String key);
	
	String rpoplpush(String srcKey, String dstKey);
	
	Long rpush(String key, String... strings);
	
	Long rpushx(String key,String value);
	
	
	byte[] lindex(byte[] key,long index);
	
	Long llen(byte[] key);
	
	byte[] lpop(byte[] key);
	
	Long lpush(byte[] key,byte[]... values);
	
	/**
	 * prepend a value to a list  only if this list exists
	 * @param key
	 * @param value
	 */
	Long lpushx(byte[] key,byte[]... values);
	
	List<byte[]> lrange(byte[] key, long start,long stop);
	
	long lrem(byte[] key,int count,byte[] value);
	
	String lset(byte[] key,int index, byte[] value);
	
	String ltrim(byte[] key,long start,long stop);
	
	byte[] rpop(byte[] key);

	Long rpush(byte[] key, byte[]... bytes);
	
	Long rpushx(byte[] key,byte[] value);

	byte[] rpoplpush(byte[] srcKey, byte[] dstKey);

	//set
	long sadd(String key,String... values);
	
	long scard(String key);
	
	boolean sismember(String key,String member);
	
	Set<String> smembers(String key);
	
	String spop(String key);

	Set<String> spop(String key,int count);
	
	String srandmember(String key);
	
	List<String> srandmember(String key,int count);
	
	long srem(String key,String... values);
	
	
	long sadd(byte[] key,byte[]... values);
	
	long scard(byte[] key);
	
	boolean sismember(byte[] key,byte[] member);
	
	Set<byte[]> smembers(byte[] key);
	
	byte[] spop(byte[] key);
	
	Set<byte[]> spop(byte[] key,int count);
	
	byte[] srandmember(byte[] key);
	
	List<byte[]> srandmember(byte[] key,int count);
	
	long srem(byte[] key,byte[]... values);
	
	//sortset
	long zadd(String key, double score, String member);
	
	long zadd(String key, Map<String, Double> scoreMembers);
	
	long zcard(String key);
	
	long zcount(String key,double minScore,double maxScore);
	
	double zincrby(String key,double increment,String member);
	
	Set<String> zrange(String key,long start,long stop);

	Set<Tuple> zrangeWithScores(String key, long start, long end);
	
	Set<String> zrangeByLex(String key,String min,String max);

	Set<String> zrangeByLex(String key,String min,String max, int offset, int count);
	
	Set<String> zrevrangeByLex(String key,String max ,String min);

	Set<String> zrevrangeByLex(String key, String max, String min, int offset, int count);
	
	Set<String> zrangeByScore(String key, double min, double max);
	
	Set<Tuple> zrangeByScoreWithScores(String key,double min ,double max);
	
	Long zrank(String key,String member);
	
	Long zrem(String key, String member);
	
	Long zremrangeByLex(String key,String min,String max);
	
	Long zremrangeByRank(String key,int start,int stop);
	
	Long zremrangebyscore(String key,double min,double max);
	
	Set<String> zrevrange(String key,int start,int stop);
	
	Set<Tuple> zrevrangeWithScores(String key,int min,int max);
	
	Long zrevrank(String key,String member);
	
	double zscore(String key,String member);
	

	long zadd(byte[] key, double score, byte[] member);

	long zadd(byte[] key, Map<byte[], Double> scoreMembers);

	long zcard(byte[] key);

	long zcount(byte[] key, double minScore, double maxScore);

	double zincrby(byte[] key, double increment, byte[] member);

	Set<byte[]> zrange(byte[] key, long start, long end);

	Set<Tuple> zrangeWithScores(byte[] key, long start, long end);

	Set<byte[]> zrangeByLex(byte[] key, byte[] min, byte[] max);

	Set<byte[]> zrangeByLex(byte[] key, byte[] min, byte[] max, int offset,
			int count);

	Set<byte[]> zrevrangeByLex(byte[] key, byte[] max, byte[] min);

	Set<byte[]> zrevrangeByLex(byte[] key, byte[] max, byte[] min, int offset,
			int count);

	Set<byte[]> zrangeByScore(byte[] key, double min, double max);

	Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max);

	Long zrank(byte[] key, byte[] member);

	Long zrem(byte[] key, byte[] member);

	Long zremrangeByLex(byte[] key, byte[] min, byte[] max);

	Long zremrangeByRank(byte[] key, int start, int end);

	Long zremrangeByScore(byte[] key, double start, double end);

	Set<byte[]> zrevrange(byte[] key, int start, int end);

	Set<Tuple> zrevrangeWithScores(byte[] key, int start, int end);

	Long zrevrank(byte[] key, byte[] member);

	double zscore(byte[] key, byte[] member);
}
