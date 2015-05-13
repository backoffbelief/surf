package com.kael.surf.logic;

import com.kael.surf.ReqProtocolBuffer.ReqUserInfo;
import com.kael.surf.net.AppPlayer;
import com.kael.surf.net.Cmd;
import com.kael.surf.net.Constants;
import com.kael.surf.net.QueueCommad;

@Cmd(code = Constants.client_req_login, protoName = "com.kael.surf.ReqProtocolBuffer$ReqUserInfo")
public class LoginCommand extends QueueCommad<AppPlayer,ReqUserInfo> {

	@Override
	public void exec(AppPlayer player, ReqUserInfo msg) {
		
	}

}
