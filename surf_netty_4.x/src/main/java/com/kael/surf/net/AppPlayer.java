package com.kael.surf.net;

import io.netty.channel.ChannelHandlerContext;

import com.kael.surf.muti.AppQueue;

public class AppPlayer {
	private AppQueue appQueue;
	private ChannelHandlerContext ctx;

	private final int playerId;
	
	public AppQueue getAppQueue() {
		return appQueue;
	}

	public AppPlayer(AppQueue appQueue,ChannelHandlerContext ctx) {
		this.appQueue = appQueue;
		this.ctx = ctx;
		playerId = ctx.channel().hashCode();
	}

	
	public void write(IMessage.Builder msg){
		ctx.writeAndFlush(msg);
	}
	
	public int getPlayerId(){
		return playerId;
	}
}
