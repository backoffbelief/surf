package com.kael.surf.net;

import io.netty.channel.ChannelHandlerContext;


public interface ICommand {
	public void messageReceive(ChannelHandlerContext ctx, Object msg);
}
