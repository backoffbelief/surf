package com.kael.surf.net.logic;

import org.springframework.stereotype.Service;

import com.kael.surf.RespProtocolBuffer;
import com.kael.surf.net.codec.IMessage;

@Service("userService")
public class UserServiceImpl {

	public void getUserInfo(String userName,int level,SynRoomPlayer player){
		player.write(IMessage.newBulider().withCode(101).withBody(RespProtocolBuffer.RespUserInfo.newBuilder().setId(1000)));
	}
}
