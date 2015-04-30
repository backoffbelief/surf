package com.kael.surf.net;

import io.netty.channel.ChannelHandlerContext;

public abstract class QueueCommad<T extends AppPlayer> extends AbstractCommand<T> {

	@Override
	public void messageReceive(ChannelHandlerContext ctx, Object msg) {
		
		T t = (T) ctx.attr(Constants.playerKey);
//		new Thread(new AppNetTask(t,this,((IMessage)msg))).start();
		new AppNetTask<T, QueueCommad<T>>(t, this, ((IMessage)msg)).checkIn();
	}

	
}
