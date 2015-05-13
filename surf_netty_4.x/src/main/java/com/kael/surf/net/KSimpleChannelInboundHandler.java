package com.kael.surf.net;

import com.kael.surf.muti.PlayerContext;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class KSimpleChannelInboundHandler extends
		SimpleChannelInboundHandler<Object> {
	private static final String REQUEST_STRING = "<policy-file-request/>" + '\0';

    private static final String RESPOND_STRING = "<cross-domain-policy><allow-access-from domain=\"*\" to-ports=\"*\"/></cross-domain-policy>" + '\0';
    
    
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelActive: from:"+ctx.channel().remoteAddress());
		AppPlayer appPlayer = new AppPlayer(PlayerContext.get().getAppQueue(), ctx);
		ctx.attr(Constants.playerKey).set(appPlayer);
		PlayerContext.get().addPlayer(appPlayer);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		System.out.println("channelRead0: from:"+ctx.channel().remoteAddress());
		
		if(msg instanceof IMessage){
			IMessage tmp = (IMessage) msg;
			PlayerContext.get().getByCode(tmp.getCode()).messageReceive(ctx, tmp);
		}else{
			if(msg instanceof String && REQUEST_STRING.equals(msg)){
				ctx.writeAndFlush(RESPOND_STRING);
				ctx.channel().close();
				return ;
			}
		}
	}

}
