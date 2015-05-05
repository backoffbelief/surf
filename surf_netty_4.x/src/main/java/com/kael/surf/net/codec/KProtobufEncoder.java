package com.kael.surf.net.codec;

import java.util.List;

import com.kael.surf.net.IMessage;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

public class KProtobufEncoder extends MessageToMessageEncoder<Object>{

	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg,
			List<Object> out) throws Exception {
		
		if(msg instanceof String){
			out.add(Unpooled.copiedBuffer(((String)msg).getBytes()));
			return ;
		}
		
		if(msg instanceof IMessage.Builder){
			IMessage m = ((IMessage.Builder)msg).build();
			byte[] b = m.getBody();
			ByteBuf bb = Unpooled.buffer().writeInt(b.length + 2).writeShort(m.getCode()).writeBytes(b);
			out.add(bb);
			return ;
		}
		
		throw new RuntimeException("unsupport encode class:"+msg.getClass().getName());
		
	}

}
