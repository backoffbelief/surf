package com.kael.surf.nosql.redis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Tuple;

public class JRedisProxy implements JRedis{
	
	
	private final int dbsize;
	
	public JRedisProxy(int dbsize) {
		super();
		this.dbsize = dbsize;
	}

	class HashRedisStrategly implements RedisPoolStrategy{

		@Override
		public JRedis select(byte[] key) {
			
			return jRedisMappers.get(key.hashCode() % dbsize);
		}

		@Override
		public JRedis select(String key) {
			
			return jRedisMappers.get(key.hashCode() % dbsize);
		}
		
	}
	
	private Map<Integer, JRedis> jRedisMappers = new HashMap<Integer, JRedis>();
	
	RedisPoolStrategy strategy = new HashRedisStrategly();
	
	
	public void addJRedis(JRedis jRedis){
		this.jRedisMappers.put(Integer.parseInt(((JRedisImpl)jRedis).getSharedName()), jRedis);
	}
	

	private JRedis getRedis(String key){
		return strategy.select(key);
	}

	private JRedis getRedis(byte[] key){
		return strategy.select(key);
	}
	
	@Override
	public void auth(String pwd) {
		
	}

	@Override
	public void bgrewriteaof() {
		
	}

	@Override
	public Long dbsize() {
		
		return null;
	}
	
	

	@Override
	public String ping() {
		return null;
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

	@Override
	public Long append(String key, String value) {
		
		return getRedis(key).append(key, value);
	}

	@Override
	public Long append(byte[] key, byte[] value) {
		
		return getRedis(key).append(key, value);
	}

	@Override
	public Long decr(String key) {
		
		return getRedis(key).decr(key);
	}

	@Override
	public Long decr(byte[] key) {
		
		return getRedis(key).decr(key);
	}

	@Override
	public Long decrby(String key, int decrment) {
		
		return getRedis(key).decrby(key,decrment);
	}

	@Override
	public Long decrby(byte[] key, int decrment) {
		
		return getRedis(key).decrby(key,decrment);
	}

	@Override
	public Long del(String... keys) {
//		int len = 0;
//		for (String key : keys) {
//			len += getJRedis(key).del(key);
//		}
		return null;
	}

	@Override
	public Long del(byte[]... keys) {
		
		return null;
	}

	@Override
	public boolean exists(String key) {
		
		return getRedis(key).exists(key);
	}

	@Override
	public boolean exists(byte[] key) {
		
		return getRedis(key).exists(key);
	}

	@Override
	public Long expire(String key, int seconds) {
		
		return getRedis(key).expire(key, seconds);
	}

	@Override
	public Long expireAt(String key, long millseconds) {
		
		return getRedis(key).expireAt(key, millseconds);
	}

	@Override
	public Long persist(String key) {
		
		return getRedis(key).persist(key);
	}

	@Override
	public Long pexpire(String key, long seconds) {
		
		return getRedis(key).pexpire(key,seconds);
	}

	@Override
	public Long pexpireAt(String key, long millseconds) {
		
		return getRedis(key).pexpireAt(key,millseconds);
	}

	@Override
	public String psetex(String key, long millseconds, String value) {
		
		return getRedis(key).psetex(key,millseconds,value);
	}

	@Override
	public long pttl(String key) {
		
		return getRedis(key).pttl(key);
	}

	@Override
	public long ttl(String key) {
		
		return getRedis(key).ttl(key);
	}

	@Override
	public Long expire(byte[] key, int seconds) {
		
		return getRedis(key).expire(key, seconds);
	}

	@Override
	public Long expireat(byte[] key, long millseconds) {
		
		return getRedis(key).expireat(key, millseconds);
	}

	@Override
	public Long persist(byte[] key) {
		
		return getRedis(key).persist(key);
	}

	@Override
	public Long pexpire(byte[] key, long seconds) {
		
		return getRedis(key).pexpire(key, seconds);
	}

	@Override
	public Long pexpireAt(byte[] key, long millseconds) {
		
		return getRedis(key).pexpireAt(key, millseconds);
	}

	@Override
	public String psetex(byte[] key, long millseconds, byte[] value) {
		
		return getRedis(key).psetex(key, millseconds, value);
	}

	@Override
	public long pttl(byte[] key) {
		
		return getRedis(key).pttl(key);
	}

	@Override
	public long ttl(byte[] key) {
		
		return getRedis(key).ttl(key);
	}

	@Override
	public String flushAll() {
		
		return null;
	}

	@Override
	public String flushDB() {
		
		return null;
	}

	@Override
	public String get(String key) {
		
		return getRedis(key).get(key);
	}

	@Override
	public byte[] get(byte[] key) {
		
		return getRedis(key).get(key);
	}

	@Override
	public String set(String key, String value) {
		
		return getRedis(key).set(key, value);
	}

	@Override
	public String set(byte[] key, byte[] value) {
		
		return getRedis(key).set(key, value);
	}

	@Override
	public Boolean getbit(String key, long offset) {
		
		return getRedis(key).getbit(key, offset);
	}

	@Override
	public String getrange(String key, int start, int end) {
		
		return getRedis(key).getrange(key, start, end);
	}

	@Override
	public String getSet(String key, String value) {
		
		return getRedis(key).getSet(key, value);
	}

	@Override
	public byte[] getSet(byte[] key, byte[] value) {
		
		return getRedis(key).getSet(key, value);
	}

	@Override
	public String setex(String key, int seconds, String value) {
		
		return getRedis(key).setex(key, seconds, value);
	}

	@Override
	public Long setnx(String key, String value) {
		
		return getRedis(key).setnx(key, value);
	}

	@Override
	public String setex(byte[] key, int seconds, byte[] value) {
		
		return getRedis(key).setex(key, seconds, value);
	}

	@Override
	public Long setnx(byte[] key, byte[] value) {
		
		return getRedis(key).setnx(key, value);
	}

	@Override
	public String randomKey() {
		
		return null;
	}

	@Override
	public Long hdel(String key, String... fields) {
		
		return getRedis(key).hdel(key, fields);
	}

	@Override
	public boolean hexist(String key, String field) {
		
		return getRedis(key).hexist(key, field);
	}

	@Override
	public String hget(String key, String field) {
		
		return getRedis(key).hget(key, field);
	}

	@Override
	public Map<String, String> hgetAll(String key) {
		
		return getRedis(key).hgetAll(key);
	}

	@Override
	public Long hincrBy(String key, String field, int increment) {
		
		return getRedis(key).hincrBy(key, field, increment);
	}

	@Override
	public Double hincrByFloat(String key, String field, double increment) {
		
		return getRedis(key).hincrByFloat(key, field, increment);
	}

	@Override
	public Set<String> hkeys(String key) {
		
		return getRedis(key).hkeys(key);
	}

	@Override
	public Long hlen(String key) {
		
		return getRedis(key).hlen(key);
	}

	@Override
	public List<String> hmget(String key, String... fields) {
		
		return getRedis(key).hmget(key, fields);
	}

	@Override
	public String hmset(String key, Map<String, String> kvstr) {
		
		return getRedis(key).hmset(key, kvstr);
	}

	@Override
	public long hset(String key, String field, String value) {
		
		return getRedis(key).hset(key, field, value);
	}

	@Override
	public Long hsetnx(String key, String field, String value) {
		
		return getRedis(key).hsetnx(key, field, value);
	}

	@Override
	public List<String> hvals(String key) {
		
		return getRedis(key).hvals(key);
	}

	@Override
	public Long hdel(byte[] key, byte[]... fields) {
		
		return getRedis(key).hdel(key, fields);
	}

	@Override
	public boolean hexist(byte[] key, byte[] field) {
		
		return getRedis(key).hexist(key, field);
	}

	@Override
	public byte[] hget(byte[] key, byte[] field) {
		
		return getRedis(key).hget(key, field);
	}

	@Override
	public Map<byte[], byte[]> hgetAll(byte[] key) {
		
		return getRedis(key).hgetAll(key);
	}

	@Override
	public Long hincrBy(byte[] key, byte[] field, int increment) {
		
		return getRedis(key).hincrBy(key, field, increment);
	}

	@Override
	public double hincrByFloat(byte[] key, byte[] field, double increment) {
		
		return getRedis(key).hincrByFloat(key, field, increment);
	}

	@Override
	public Set<byte[]> hkeys(byte[] key) {
		
		return getRedis(key).hkeys(key);
	}

	@Override
	public Long hlen(byte[] key) {
		
		return getRedis(key).hlen(key);
	}

	@Override
	public List<byte[]> hmget(byte[] key, byte[]... fields) {
		
		return getRedis(key).hmget(key, fields);
	}

	@Override
	public String hmset(byte[] key, Map<byte[], byte[]> kvBytes) {
		
		return getRedis(key).hmset(key, kvBytes);
	}

	@Override
	public long hset(byte[] key, byte[] field, byte[] value) {
		
		return getRedis(key).hset(key, field, value);
	}

	@Override
	public Long hsetnx(byte[] key, byte[] field, byte[] value) {
		
		return getRedis(key).hsetnx(key, field, value);
	}

	@Override
	public List<byte[]> hvals(byte[] key) {
		
		return getRedis(key).hvals(key);
	}

	@Override
	public long incr(String key) {
		
		return getRedis(key).incr(key);
	}

	@Override
	public long incr(byte[] key) {
		
		return getRedis(key).incr(key);
	}

	@Override
	public long incrBy(String key, int increment) {
		
		return getRedis(key).incrBy(key, increment);
	}

	@Override
	public long incrBy(byte[] key, int increment) {
		
		return getRedis(key).incrBy(key, increment);
	}

	@Override
	public Double incrByFloat(String key, double increment) {
		
		return getRedis(key).incrByFloat(key, increment);
	}

	@Override
	public Double incrByFloat(byte[] key, double increment) {
		
		return getRedis(key).incrByFloat(key, increment);
	}

	@Override
	public Set<String> keys(String pattern) {
		
		return null;
	}

	@Override
	public Set<byte[]> keys(byte[] pattern) {
		
		return null;
	}

	@Override
	public List<String> mget(String... keys) {
		List<String> result = new ArrayList<String>(keys.length);
		for (String key : keys) {
			result.add(get(key));
		}
		return result;
	}

	@Override
	public String mset(String... keysvalues) {
		if(keysvalues.length %2 != 0){
			throw new KRedisException("mset", "keysvalues.length %2 != 0", "", null);
		}
		
		for (int i = 0 ;i < keysvalues.length;) {
			set(keysvalues[i], keysvalues[i + 1]);
			i += 2;
		}
		return null;
	}

	@Override
	public Long msetnx(String... msetnx) {
		
		return null;
	}

	@Override
	public List<byte[]> mget(byte[]... keys) {
		
		return null;
	}

	@Override
	public String mset(byte[]... keysvalues) {
		
		return null;
	}

	@Override
	public Long msetnx(byte[]... msetnx) {
		
		return null;
	}

	@Override
	public long lastsave() {
		
		return 0;
	}

	@Override
	public String lindex(String key, long index) {
		
		return getRedis(key).lindex(key, index);
	}

	@Override
	public Long llen(String key) {
		
		return getRedis(key).llen(key);
	}

	@Override
	public String lpop(String key) {
		
		return getRedis(key).lpop(key);
	}

	@Override
	public Long lpush(String key, String... values) {
		
		return getRedis(key).lpush(key, values);
	}

	@Override
	public Long lpushx(String key, String... string) {
		
		return getRedis(key).lpushx(key, string);
	}

	@Override
	public List<String> lrange(String key, long start, long end) {
		
		return getRedis(key).lrange(key, start, end);
	}

	@Override
	public Long lrem(String key, int count, String value) {
		
		return getRedis(key).lrem(key, count, value);
	}

	@Override
	public String lset(String key, int index, String value) {
		
		return getRedis(key).lset(key, index, value);
	}

	@Override
	public String ltrim(String key, long start, long end) {
		
		return getRedis(key).ltrim(key, start, end);
	}

	@Override
	public String rpop(String key) {
		
		return getRedis(key).rpop(key);
	}

	@Override
	public String rpoplpush(String srcKey, String dstKey) {
		String value = rpop(srcKey);
		lpush(dstKey, value);
		return value;
	}

	@Override
	public Long rpush(String key, String... strings) {
		
		return getRedis(key).rpush(key, strings);
	}

	@Override
	public Long rpushx(String key, String value) {
		
		return getRedis(key).rpushx(key, value);
	}

	@Override
	public byte[] lindex(byte[] key, long index) {
		
		return getRedis(key).lindex(key, index);
	}

	@Override
	public Long llen(byte[] key) {
		
		return getRedis(key).llen(key);
	}

	@Override
	public byte[] lpop(byte[] key) {
		
		return getRedis(key).lpop(key);
	}

	@Override
	public Long lpush(byte[] key, byte[]... values) {
		
		return getRedis(key).lpush(key, values);
	}

	@Override
	public Long lpushx(byte[] key, byte[]... values) {
		
		return getRedis(key).lpushx(key, values);
	}

	@Override
	public List<byte[]> lrange(byte[] key, long start, long stop) {
		
		return getRedis(key).lrange(key, start, stop);
	}

	@Override
	public long lrem(byte[] key, int count, byte[] value) {
		
		return getRedis(key).lrem(key, count, value);
	}

	@Override
	public String lset(byte[] key, int index, byte[] value) {
		
		return getRedis(key).lset(key, index, value);
	}

	@Override
	public String ltrim(byte[] key, long start, long stop) {
		
		return getRedis(key).ltrim(key, start, stop);
	}

	@Override
	public byte[] rpop(byte[] key) {
		
		return getRedis(key).rpop(key);
	}

	@Override
	public Long rpush(byte[] key, byte[]... bytes) {
		
		return getRedis(key).rpush(key, bytes);
	}

	@Override
	public Long rpushx(byte[] key, byte[] value) {
		
		return getRedis(key).rpushx(key, value);
	}

	@Override
	public byte[] rpoplpush(byte[] srcKey, byte[] dstKey) {
		byte[] values = rpop(srcKey);
		lpush(dstKey, values);
		return values;
	}

	@Override
	public long sadd(String key, String... values) {
		
		return getRedis(key).sadd(key, values);
	}

	@Override
	public long scard(String key) {
		
		return getRedis(key).scard(key);
	}

	@Override
	public boolean sismember(String key, String member) {
		
		return getRedis(key).sismember(key, member);
	}

	@Override
	public Set<String> smembers(String key) {
		
		return getRedis(key).smembers(key);
	}

	@Override
	public String spop(String key) {
		
		return getRedis(key).spop(key);
	}

	@Override
	public Set<String> spop(String key, int count) {
		
		return getRedis(key).spop(key, count);
	}

	@Override
	public String srandmember(String key) {
		
		return getRedis(key).srandmember(key);
	}

	@Override
	public List<String> srandmember(String key, int count) {
		
		return getRedis(key).srandmember(key, count);
	}

	@Override
	public long srem(String key, String... values) {
		
		return getRedis(key).srem(key, values);
	}

	@Override
	public long sadd(byte[] key, byte[]... values) {
		
		return getRedis(key).sadd(key, values);
	}

	@Override
	public long scard(byte[] key) {
		
		return getRedis(key).scard(key);
	}

	@Override
	public boolean sismember(byte[] key, byte[] member) {
		
		return getRedis(key).sismember(key, member);
	}

	@Override
	public Set<byte[]> smembers(byte[] key) {
		
		return getRedis(key).smembers(key);
	}

	@Override
	public byte[] spop(byte[] key) {
		
		return getRedis(key).spop(key);
	}

	@Override
	public Set<byte[]> spop(byte[] key, int count) {
		
		return getRedis(key).spop(key, count);
	}

	@Override
	public byte[] srandmember(byte[] key) {
		
		return getRedis(key).srandmember(key);
	}

	@Override
	public List<byte[]> srandmember(byte[] key, int count) {
		
		return getRedis(key).srandmember(key, count);
	}

	@Override
	public long srem(byte[] key, byte[]... values) {
		
		return getRedis(key).srem(key, values);
	}

	@Override
	public long zadd(String key, double score, String member) {
		
		return getRedis(key).zadd(key, score, member);
	}

	@Override
	public long zadd(String key, Map<String, Double> scoreMembers) {
		
		return getRedis(key).zadd(key, scoreMembers);
	}

	@Override
	public long zcard(String key) {
		
		return getRedis(key).zcard(key);
	}

	@Override
	public long zcount(String key, double minScore, double maxScore) {
		
		return getRedis(key).zcount(key, minScore, maxScore);
	}

	@Override
	public double zincrby(String key, double increment, String member) {
		
		return getRedis(key).zincrby(key, increment, member);
	}

	@Override
	public Set<String> zrange(String key, long start, long stop) {
		
		return getRedis(key).zrange(key, start, stop);
	}

	@Override
	public Set<Tuple> zrangeWithScores(String key, long start, long end) {
		
		return getRedis(key).zrangeByScoreWithScores(key, start, end);
	}

	@Override
	public Set<String> zrangeByLex(String key, String min, String max) {
		
		return getRedis(key).zrangeByLex(key, min, max);
	}

	@Override
	public Set<String> zrangeByLex(String key, String min, String max,
			int offset, int count) {
		
		return getRedis(key).zrangeByLex(key, min, max, offset, count);
	}

	@Override
	public Set<String> zrevrangeByLex(String key, String max, String min) {
		
		return getRedis(key).zrevrangeByLex(key, max, min);
	}

	@Override
	public Set<String> zrevrangeByLex(String key, String max, String min,
			int offset, int count) {
		
		return getRedis(key).zrevrangeByLex(key, max, min, offset, count);
	}

	@Override
	public Set<String> zrangeByScore(String key, double min, double max) {
		
		return getRedis(key).zrangeByScore(key, min, max);
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
		
		return getRedis(key).zrangeByScoreWithScores(key, min, max);
	}

	@Override
	public Long zrank(String key, String member) {
		
		return getRedis(key).zrank(key, member);
	}

	@Override
	public Long zrem(String key, String member) {
		
		return getRedis(key).zrem(key, member);
	}

	@Override
	public Long zremrangeByLex(String key, String min, String max) {
		
		return getRedis(key).zremrangeByLex(key, min, max);
	}

	@Override
	public Long zremrangeByRank(String key, int start, int stop) {
		
		return getRedis(key).zremrangeByRank(key, start, stop);
	}

	@Override
	public Long zremrangebyscore(String key, double min, double max) {
		
		return getRedis(key).zremrangebyscore(key, min, max);
	}

	@Override
	public Set<String> zrevrange(String key, int start, int stop) {
		
		return getRedis(key).zrevrange(key, start, stop);
	}

	@Override
	public Set<Tuple> zrevrangeWithScores(String key, int min, int max) {
		
		return getRedis(key).zrevrangeWithScores(key, min, max);
	}

	@Override
	public Long zrevrank(String key, String member) {
		
		return getRedis(key).zrevrank(key, member);
	}

	@Override
	public double zscore(String key, String member) {
		
		return getRedis(key).zscore(key, member);
	}

	@Override
	public long zadd(byte[] key, double score, byte[] member) {
		
		return getRedis(key).zadd(key, score, member);
	}

	@Override
	public long zadd(byte[] key, Map<byte[], Double> scoreMembers) {
		
		return getRedis(key).zadd(key, scoreMembers);
	}

	@Override
	public long zcard(byte[] key) {
		
		return getRedis(key).zcard(key);
	}

	@Override
	public long zcount(byte[] key, double minScore, double maxScore) {
		
		return getRedis(key).zcount(key, minScore, maxScore);
	}

	@Override
	public double zincrby(byte[] key, double increment, byte[] member) {
		
		return getRedis(key).zincrby(key, increment, member);
	}

	@Override
	public Set<byte[]> zrange(byte[] key, long start, long end) {
		
		return getRedis(key).zrange(key, start, end);
	}

	@Override
	public Set<Tuple> zrangeWithScores(byte[] key, long start, long end) {
		
		return getRedis(key).zrangeWithScores(key, start, end);
	}

	@Override
	public Set<byte[]> zrangeByLex(byte[] key, byte[] min, byte[] max) {
		
		return getRedis(key).zrangeByLex(key, min, max);
	}

	@Override
	public Set<byte[]> zrangeByLex(byte[] key, byte[] min, byte[] max,
			int offset, int count) {
		
		return getRedis(key).zrangeByLex(key, min, max, offset, count);
	}

	@Override
	public Set<byte[]> zrevrangeByLex(byte[] key, byte[] max, byte[] min) {
		
		return getRedis(key).zrevrangeByLex(key, max, min);
	}

	@Override
	public Set<byte[]> zrevrangeByLex(byte[] key, byte[] max, byte[] min,
			int offset, int count) {
		
		return getRedis(key).zrevrangeByLex(key, max, min, offset, count);
	}

	@Override
	public Set<byte[]> zrangeByScore(byte[] key, double min, double max) {
		
		return getRedis(key).zrangeByScore(key, min, max);
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max) {
		
		return getRedis(key).zrangeByScoreWithScores(key, min, max);
	}

	@Override
	public Long zrank(byte[] key, byte[] member) {
		
		return getRedis(key).zrank(key, member);
	}

	@Override
	public Long zrem(byte[] key, byte[] member) {
		
		return getRedis(key).zrem(key, member);
	}

	@Override
	public Long zremrangeByLex(byte[] key, byte[] min, byte[] max) {
		
		return getRedis(key).zremrangeByLex(key, min, max);
	}

	@Override
	public Long zremrangeByRank(byte[] key, int start, int end) {
		
		return getRedis(key).zremrangeByRank(key, start, end);
	}

	@Override
	public Long zremrangeByScore(byte[] key, double start, double end) {
		
		return getRedis(key).zremrangeByScore(key, start, end);
	}

	@Override
	public Set<byte[]> zrevrange(byte[] key, int start, int end) {
		
		return getRedis(key).zrevrange(key, start, end);
	}

	@Override
	public Set<Tuple> zrevrangeWithScores(byte[] key, int start, int end) {
		
		return getRedis(key).zrevrangeWithScores(key, start, end);
	}

	@Override
	public Long zrevrank(byte[] key, byte[] member) {
		
		return getRedis(key).zrevrank(key, member);
	}

	@Override
	public double zscore(byte[] key, byte[] member) {
		
		return getRedis(key).zscore(key, member);
	}

}
