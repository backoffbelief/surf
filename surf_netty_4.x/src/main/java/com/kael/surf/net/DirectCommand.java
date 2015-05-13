package com.kael.surf.net;

import io.netty.channel.ChannelHandlerContext;

import com.google.protobuf.GeneratedMessage;

public abstract class DirectCommand<T extends AppPlayer,M extends GeneratedMessage> extends AbstractCommand<T, M> {

	@Override
	public void messageReceive(ChannelHandlerContext ctx, Object msg) {
		T t = (T) ctx.attr(Constants.playerKey);
		if(t == null){
			throw new RuntimeException("not exist player in session!");
		}
		
		if(msg == null){
			throw new RuntimeException("msg == null!");
		}
		
		try {
			this.exec(t, beforeExec((IMessage)msg));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
