package com.kael.surf.net.codec;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Message;
import com.kael.surf.net.config.ReqCmdProperties;

public class IMessage {
	private int code;
	
	private byte[] body;
	
	public static Builder newBulider(){
		return new Builder();
	}
	
	public static class Builder {
		private final IMessage iMessage;

		public Builder() {
			this.iMessage = new IMessage();
		}
		
		public Builder withCode(int code){
			iMessage.code = code;
			return this;
		}

		public Builder withBody(byte[] datas){
			iMessage.body = datas;
			return this;
		}

		public Builder withBody(Message.Builder builder){
			iMessage.body = builder.build().toByteArray();
			return this;
		}
		
		public IMessage build(){
			return iMessage;
		}
	}

	public int getCode() {
		return code;
	}

	public byte[] getBody() {
		return body;
	}

	public GeneratedMessage paser(ReqCmdProperties cmdProperties) throws Exception {
		 GeneratedMessage proto = (GeneratedMessage) cmdProperties.getDefaultInstance().newBuilderForType()
                 .mergeFrom(body).build();
		return proto;
	}

}
