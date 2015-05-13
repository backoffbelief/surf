package com.kael.surf.net;

import java.util.Set;

import com.kael.surf.muti.PlayerContext;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class AppServer {

	public static void main(String[] args) {
		Set<Class<?>> clazzes = Scan.getClasses("com.kael");

		NioEventLoopGroup parentGroup = new NioEventLoopGroup(1);
		NioEventLoopGroup childGroup = new NioEventLoopGroup(2);

		try {
			PlayerContext.get().init(clazzes);
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(parentGroup, childGroup)
					.channel(NioServerSocketChannel.class)
					.childHandler(new IOChannelInitializer())
					.option(ChannelOption.SO_BACKLOG, 128)  
					.childOption(ChannelOption.SO_KEEPALIVE, true);
			bootstrap.bind(8888).sync();
			System.out.println("init success!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			parentGroup.shutdownGracefully();
//			childGroup.shutdownGracefully();
		}
	}
}
