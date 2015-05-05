package com.kael.surf.net;

public class IMessage {
	private IMessage() {
	}
	
	public static Builder create(){
		return new Builder();
	}

	public static class Builder{
		private final IMessage msg;

		private Builder() {
			msg = new IMessage();
		}
		
		public Builder setCode(short code) {
			msg.code = code;
			return this;
		}

		public Builder setBody(byte[] body) {
			msg.body = body;
			return this;
		}
		
		public IMessage build(){
			return msg;
		}
	}
	
	private short code;
	
	private byte[] body;

	public short getCode() {
		return code;
	}

	public byte[] getBody() {
		return body;
	}

}
