package com.kael.surf.net;

import com.google.protobuf.GeneratedMessage;

import io.netty.channel.ChannelHandlerContext;

public abstract class QueueCommad<T extends AppPlayer,M extends GeneratedMessage> extends AbstractCommand<T,M> {

	@Override
	public void messageReceive(ChannelHandlerContext ctx, Object msg) {
		
		T t = (T) ctx.attr(Constants.playerKey).get();
		if(t == null){
			throw new RuntimeException("not exist player in session!");
		}
		
		if(msg == null){
			throw new RuntimeException("msg == null!");
		}
		
		try {
			new AppNetTask<T, M,QueueCommad<T,M>>(t, this, beforeExec((IMessage)msg)).checkIn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
