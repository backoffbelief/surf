package com.kael.surf.net;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kael.surf.net.config.CmdsConfig;
import com.kael.surf.net.dispatcher.CmdDispatcher;
import com.kael.surf.net.logic.AppServerPipelineFactory;
import com.kael.surf.net.logic.AppServerUpstreamHandler;
import com.kael.surf.net.mutithread.GlobalBusinessThreadPool;

/**
 * Hello world!
 *
 */
public class App 
{
	private static Logger logger = Logger.getLogger("App");
	
    public static void main( String[] args )
    {
    	 ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    	 ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(Executors
                 .newCachedThreadPool(), Executors.newCachedThreadPool()));
//         Syn3v3FightServerContext context = (Syn3v3FightServerContext) ac.getBean("syn3v3FightServerContext");
         CmdsConfig cmdsConfig = new CmdsConfig("reqCMD.xml", logger);
//         SynRoomBusinessThreadPool businessThreadPool = new SynRoomBusinessThreadPool(Syn3v3FightConfig.getBusinessThreadPoolCoreThreadNum(), Syn3v3FightConfig.getBusinessThreadPoolMaxThreadNum(), Syn3v3FightConfig.getBusinessThreadPoolQueueSize());
         GlobalBusinessThreadPool globalBusinessThreadPool = new GlobalBusinessThreadPool(1, 3);
//         context.setBusinessThreadPool(businessThreadPool);
//         context.setGlobalBusinessThreadPool(globalBusinessThreadPool);
//         Syn3v3FightExceptionHandler exceptionHandler = new Syn3v3FightExceptionHandler();
         CmdDispatcher dispatcher = new CmdDispatcher(cmdsConfig,  globalBusinessThreadPool);
         dispatcher.init(ac);
//         Syn3v3GlobalServiceImpl globalServiceImpl = (Syn3v3GlobalServiceImpl) ac.getBean("syn3v3GlobalService");
//         globalServiceImpl.init(businessThreadPool,globalBusinessThreadPool);
         AppServerUpstreamHandler handler = new AppServerUpstreamHandler(dispatcher);
//         Global3v3 global = Global3v3.getInstance();
//         global.startServer();
         bootstrap.setPipelineFactory(new AppServerPipelineFactory(handler));
         bootstrap.setOption("child.tcpNoDelay", true);
         bootstrap.setOption("child.receiveBufferSize", 1048576);
         InetSocketAddress address = new InetSocketAddress(7788);
         bootstrap.bind(address);
         logger.info("app server start...");
//         Syn3v3ScheduleEventServiceImpl scheduleEventServiceImpl = (Syn3v3ScheduleEventServiceImpl) ac.getBean("syn3v3ScheduleEventService");
//         scheduleEventServiceImpl.init(businessThreadPool, global);
    }
}
