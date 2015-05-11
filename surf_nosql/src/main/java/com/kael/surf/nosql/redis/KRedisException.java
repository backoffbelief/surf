package com.kael.surf.nosql.redis;

import java.util.Arrays;

public class KRedisException extends RuntimeException {
	private static final long serialVersionUID = -5974425218070576802L;

	public KRedisException() {
		super();
		
	}
	public KRedisException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public KRedisException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public KRedisException(String message) {
		super(message);
		
	}

	public KRedisException(Throwable cause) {
		super(cause);
		
	}
	
	public KRedisException(String cmds,String key,String arguments, Throwable cause) {
		this(String.format("cmds:[%s],key:[%s],args:[%s]", cmds,key,arguments), cause);
	}

	public KRedisException(String cmds,byte[] key,String arguments, Throwable cause) {
		this(String.format("cmds:[%s],key:[%s],args:[%s]", cmds,Arrays.toString(key),arguments), cause);
	}

}
