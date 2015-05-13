package com.kael.surf.net.codec;

import java.util.Arrays;
import java.util.List;

import com.kael.surf.net.IMessage;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
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
			throw new RuntimeException("unsupport decode java class: "+msg.getClass().getName());
		}
		
		ByteBuf target = Unpooled.copiedBuffer((ByteBuf) msg);
		IMessage.Builder builder = IMessage.create().setCode(target.readShort());
		int index = target.readerIndex() + target.arrayOffset();
		byte[] dst = new byte[target.readableBytes()];
		target.getBytes(index, dst, 0, dst.length);
		builder.setBody(dst);
		System.out.println(Arrays.toString(dst));
		out.add(builder.build());
	}

}
