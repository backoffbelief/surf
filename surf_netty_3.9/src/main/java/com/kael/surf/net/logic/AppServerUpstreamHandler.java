package com.kael.surf.net.logic;

import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.kael.surf.net.codec.IMessage;
import com.kael.surf.net.dispatcher.CmdDispatcher;

/**
 * 同步3v3的消息处理者，从客户端传过来的消息，decode后首先会经过这里
 *
 */
public class AppServerUpstreamHandler extends SimpleChannelUpstreamHandler {
    private final CmdDispatcher CMD_DISPATCHER;

    
    private static final String REQUEST_STRING = "<policy-file-request/>" + '\0';

    private static final String RESPOND_STRING = "<cross-domain-policy><allow-access-from domain=\"*\" to-ports=\"*\"/></cross-domain-policy>" + '\0';
    
    
    public AppServerUpstreamHandler(CmdDispatcher dispatcher) {
        this.CMD_DISPATCHER = dispatcher;
    }
    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channel connected:" + ctx.getChannel());
        SynRoomPlayer player = new SynRoomPlayer(ctx.getChannel().getId(), ctx);
        ctx.setAttachment(player);
    }
    
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        SynRoomPlayer synRoomPlayer = (SynRoomPlayer) ctx.getAttachment();
        if(e.getMessage() instanceof IMessage) {
        	IMessage cmd = (IMessage) e.getMessage();
            if (synRoomPlayer == null) {
                System.err.println("get synRoomPlayer fail but we an msg, do not process");
                ctx.getChannel().close();
            } else {
                CMD_DISPATCHER.dispatch(synRoomPlayer, cmd);
            }
        } else {
            String msg = (String) e.getMessage();
            if (REQUEST_STRING.equals(msg)) {
                ctx.getChannel().write(RESPOND_STRING).addListener(ChannelFutureListener.CLOSE);
            } else {
                ctx.getChannel().close();
            }
        }
    }
    
    
    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelDisconnected" + e.getChannel());
        SynRoomPlayer synRoomPlayer = (SynRoomPlayer) ctx.getAttachment();
        if (synRoomPlayer == null) { //had deal
        } else {
        	ctx.setAttachment(null);
        }
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
    	e.getCause().printStackTrace();
    }
    
}