package com.kael.surf.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class AppServer {

	public static void main(String[] args) {
		NioEventLoopGroup parentGroup = new NioEventLoopGroup(1);
		NioEventLoopGroup childGroup = new NioEventLoopGroup(2);

		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(parentGroup, childGroup)
					.channel(NioServerSocketChannel.class)
					.childHandler(new IOChannelInitializer())
					.option(ChannelOption.SO_BACKLOG, 128)  
					.childOption(ChannelOption.SO_KEEPALIVE, true);
			bootstrap.bind(8888);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			parentGroup.shutdownGracefully();
			childGroup.shutdownGracefully();
		}
	}
}
