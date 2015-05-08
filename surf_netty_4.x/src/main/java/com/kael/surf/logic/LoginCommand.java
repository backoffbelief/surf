package com.kael.surf.logic;

import com.kael.surf.net.AppPlayer;
import com.kael.surf.net.Cmd;
import com.kael.surf.net.IMessage;
import com.kael.surf.net.QueueCommad;
@Cmd(code=1,protoName="ddddddddddddd")
public class LoginCommand extends QueueCommad<AppPlayer> {

	@Override
	public void exec(AppPlayer player, IMessage msg) {
		
	}

}
