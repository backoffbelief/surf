package com.kael.surf.net;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

import com.kael.surf.net.codec.KProtobufDecoder;
import com.kael.surf.net.codec.KProtobufEncoder;
import com.kael.surf.net.codec.LengthFieldBasedFrameDecoder;

class IOChannelInitializer extends
			ChannelInitializer<SocketChannel> {
		@Override
		protected void initChannel(SocketChannel ch)
				throws Exception {
			ChannelPipeline cp = ch.pipeline();
			cp.addLast("framedecoder", new LengthFieldBasedFrameDecoder(65536, 0, 4, 0, 4));
			cp.addLast("decode", new KProtobufDecoder());
			cp.addLast("encoder",new KProtobufEncoder());
			cp.addLast("handler", new KSimpleChannelInboundHandler());
		}
	}