package com.kael.surf.net.logic;

import org.jboss.netty.channel.ChannelHandlerContext;

import com.kael.surf.net.codec.IMessage.Builder;

public class SynRoomPlayer {
    protected long playerId; //channelId
    
    protected ChannelHandlerContext channelHandlerCtx;
    
//    protected AtomicReference<SynRoom> location = new AtomicReference<SynRoom>();

    public SynRoomPlayer(long playerId, ChannelHandlerContext channelHandlerContext) {
        this.playerId = playerId;
        this.channelHandlerCtx = channelHandlerContext;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public ChannelHandlerContext getChannelHandlerCtx() {
        return channelHandlerCtx;
    }

    public void setChannelHandlerCtx(ChannelHandlerContext channelHandlerCtx) {
        this.channelHandlerCtx = channelHandlerCtx;
    }

	public void write(Builder withBody) {
		this.channelHandlerCtx.getChannel().write(withBody);
	}

//    public void setLocation(SynRoom location) {
//        this.location.set(location);
//    }
//    
//    public SynRoom getLocation() {
//        return this.location.get();
//    }
}
