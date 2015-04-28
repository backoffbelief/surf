package com.kael.surf.net.codec;

import java.util.Arrays;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;

public class KProtobufDecoder extends OneToOneDecoder {

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
		if (!(msg instanceof ChannelBuffer)) {
            return msg;
        }
        ChannelBuffer buf = (ChannelBuffer) msg;
        if (buf.hasArray()) {
        	IMessage.Builder builder = IMessage.newBulider();
        	builder.withCode(buf.readShort() & 0xffff);
        	int from = buf.arrayOffset() + buf.readerIndex();
        	builder.withBody(Arrays.copyOfRange(buf.array(), from,from + buf.readableBytes()));
        	return builder.build();
        }
		return null;
	}

}
