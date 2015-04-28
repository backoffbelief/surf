package com.kael.surf.net.codec;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

public class KProtobufEncoder extends OneToOneEncoder {

	private static final String RESPOND_STRING = "<cross-domain-policy><allow-access-from domain=\"*\" to-ports=\"*\"/></cross-domain-policy>" + '\0';
	private final ChannelBuffer buf;

	public KProtobufEncoder() {
		buf = ChannelBuffers.buffer(RESPOND_STRING.getBytes().length);
		buf.writeBytes(RESPOND_STRING.getBytes());
	}

	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
		if (!(msg instanceof IMessage.Builder)) {
			if (msg instanceof String) {
				if (msg.equals(RESPOND_STRING)) {
					return buf;
				}
			}
			// System.out.println(msg);
			//logger.warn("someone want to send an unsupport msg type:" + msg);
			return ChannelBuffers.buffer(0);
		}
		IMessage tmp = ((IMessage.Builder)msg).build();
		byte[] bytes = tmp.getBody();
		ChannelBuffer buf = ChannelBuffers.buffer(6 + bytes.length);//一个整形长度，1个byte的类型
        buf.writeInt(bytes.length + 2);//数据包括 type + data
        buf.writeShort(tmp.getCode());
        buf.writeBytes(bytes);
		return buf;
	}

}
