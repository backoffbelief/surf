package com.kael.surf.net.logic;

import org.apache.log4j.Logger;
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
    private static Logger logger = Logger.getLogger("synfight");

//    private final Global3v3 global = Global3v3.getInstance();

    private final CmdDispatcher CMD_DISPATCHER;

//    private final ExceptionHandler EXCEPTION_HANDLER;
    
    private static final String REQUEST_STRING = "<policy-file-request/>" + '\0';

    private static final String RESPOND_STRING = "<cross-domain-policy><allow-access-from domain=\"*\" to-ports=\"*\"/></cross-domain-policy>" + '\0';
    
//    private UserRepo userRepo;
//
//    private SynFightRepo synFightRepo;
//
//    private OfficerRepo officerRepo;
//    
//    private BarrackRepo barrackRepo;
//    
//    private TrackDomainService trackDomainService;

    
    public AppServerUpstreamHandler(CmdDispatcher dispatcher/**, ExceptionHandler exceptionHandler,ApplicationContext ac*/) {
        this.CMD_DISPATCHER = dispatcher;
//        this.EXCEPTION_HANDLER = exceptionHandler;
        
//        this.userRepo=(UserRepo)ac.getBean("userRepo");
//        this.synFightRepo=(SynFightRepo)ac.getBean("synFightRepo");
//        this.officerRepo=(OfficerRepo)ac.getBean("officerRepo");
//        this.barrackRepo=(BarrackRepo)ac.getBean("barrackRepo");
//        this.trackDomainService=(TrackDomainService)ac.getBean("trackDomainService");
    }
    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        logger.debug("channel connected:" + ctx.getChannel());
//        ServerState serverState = global.getServerState();
//        if (serverState != ServerState.running) {
//            Syn3v3ErrorCodeProto.Builder builder = Syn3v3ErrorCodeProto.newBuilder();
//            builder.setErrorCode(ErrorCode.SERVER_CONNECT_CLOSE);
//            ctx.getChannel().write(builder).addListener(ChannelFutureListener.CLOSE);
//            return;
//        }
//        if (global.getConnectedGamePlayers().size() >= Syn3v3FightConfig.getMaxRealConnectUserNum()) {
//            ErrorCodeProto.Builder builder = ErrorCodeProto.newBuilder();
//            builder.setErrorCode(ErrorCode.SERVER_FULL_TRY_AGAIN_LATER);
//            ctx.getChannel().write(builder).addListener(ChannelFutureListener.CLOSE);
//            return;
//        }
//        Syn3v3FightPlayer synRoomPlayer = new Syn3v3FightPlayer(ctx.getChannel().getId(), ctx, Syn3v3RoomPlayerState.connected);
//        synRoomPlayer.initRepo(userRepo, synFightRepo, officerRepo, barrackRepo, trackDomainService);
//        ctx.setAttachment(synRoomPlayer);
//        if (!global.addConnectedGamePlayer(synRoomPlayer)) {
//            logger.error("someone connected,but have a player with the same playerId(channelId)");
//            ctx.setAttachment(null);
//            ctx.getChannel().close();
//        }
        SynRoomPlayer player = new SynRoomPlayer(ctx.getChannel().getId(), ctx);
        ctx.setAttachment(player);
    }
    
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        SynRoomPlayer synRoomPlayer = (SynRoomPlayer) ctx.getAttachment();
        if(e.getMessage() instanceof IMessage) {
        	IMessage cmd = (IMessage) e.getMessage();
            if (synRoomPlayer == null) {
                logger.error("get synRoomPlayer fail but we an msg, do not process");
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
        logger.debug("channelDisconnected" + e.getChannel());
        SynRoomPlayer synRoomPlayer = (SynRoomPlayer) ctx.getAttachment();
        if (synRoomPlayer == null) { //had deal
        } else {
//            synRoomPlayer.errorExit(global);
        	ctx.setAttachment(null);
        }
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
//        Throwable exception = e.getCause();
//        EXCEPTION_HANDLER.handleException(exception, ctx);
    	e.getCause().printStackTrace();
    }
    
}