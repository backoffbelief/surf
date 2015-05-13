package com.kael.surf.net;

import io.netty.util.AttributeKey;

public interface Constants {
	
	Constants constants = new Constants(){};
	
	AttributeKey<AppPlayer> playerKey = AttributeKey.valueOf("player");
	
	short client_req_login = 1;
	
	@Resp(protoName="com.kael.surf.RespProtocolBuffer$RespUserInfo")
	short server_resp_login = 101;
}
