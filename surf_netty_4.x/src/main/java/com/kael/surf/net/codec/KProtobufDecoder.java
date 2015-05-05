package com.kael.surf.net.codec;

import java.util.List;

import com.kael.surf.net.IMessage;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

public class KProtobufDecoder extends MessageToMessageDecoder<Object> {

	@Override
	protected void decode(ChannelHandlerContext ctx, Object msg,
			List<Object> out) throws Exception {
		if(!(msg instanceof ByteBuf) ){
			if(msg instanceof String){
				out.add(msg);
				return ;
			}
			throw new RuntimeException("unsupport java class: "+msg.getClass().getName());
		}
		
		ByteBuf tmp = (ByteBuf) msg;
		IMessage.Builder builder = IMessage.create().setCode(tmp.readShort());
		int index = tmp.readerIndex() + tmp.arrayOffset();
		byte[] dst = new byte[tmp.readableBytes()];
		tmp.getBytes(index, dst, 0, dst.length);
		builder.setBody(dst);
		out.add(builder.build());
	}

}
