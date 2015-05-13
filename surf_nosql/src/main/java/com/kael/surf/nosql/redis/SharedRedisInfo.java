package com.kael.surf.nosql.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Protocol;

public class SharedRedisInfo {

	private int timeout;
	private String masterHost;
	private int masterPort;
	private String slaveHost;
	private int slavePort;
	private final String sharedName;
	
	private static final int defaultTimeOut = Protocol.DEFAULT_TIMEOUT;
	private static final int defaultRedisPort = Protocol.DEFAULT_PORT;
	
	public SharedRedisInfo(String sharedName,int timeout, String masterHost, int masterPort,
			String slaveHost, int slavePort) {
		super();
		this.timeout = timeout;
		this.masterHost = masterHost;
		this.masterPort = masterPort;
		this.slaveHost = slaveHost;
		this.slavePort = slavePort;
		this.sharedName = sharedName;
	}
	
	public SharedRedisInfo(String sharedName,String masterHost, String slaveHost){
		this(sharedName,defaultTimeOut, masterHost, defaultRedisPort, slaveHost, defaultRedisPort);
	}
	
	public SharedRedisInfo(String sharedName,String masterHost,int masterPort){
		this(sharedName,masterHost, masterPort, masterHost, masterPort);
	}
	
	public SharedRedisInfo(String sharedName,String masterHost, int masterPort,String slaveHost, int slavePort){
		this(sharedName,defaultTimeOut, masterHost, masterPort, slaveHost, slavePort);
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public String getMasterHost() {
		return masterHost;
	}

	public void setMasterHost(String masterHost) {
		this.masterHost = masterHost;
	}

	public int getMasterPort() {
		return masterPort;
	}

	public void setMasterPort(int masterPort) {
		this.masterPort = masterPort;
	}

	public String getSlaveHost() {
		return slaveHost;
	}

	public void setSlaveHost(String slaveHost) {
		this.slaveHost = slaveHost;
	}

	public int getSlavePort() {
		return slavePort;
	}

	public void setSlavePort(int slavePort) {
		this.slavePort = slavePort;
	}
	
	public String getSharedName() {
		return sharedName;
	}

	public Jedis[] createJedisClients(){
		Jedis[] ret = new Jedis[2];
		ret[0] = new Jedis(masterHost, masterPort, timeout);
		ret[1] = new Jedis(slaveHost, slavePort, timeout);
		return ret;
	}
}
