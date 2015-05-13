package com.kael.surf.net;

import com.google.protobuf.GeneratedMessage;

public abstract class AbstractCommand<T extends AppPlayer,M extends GeneratedMessage> implements ICommand {
	protected M defaultInstance;
	
	public void setDefaultInstance(M defaultInstance) {
		this.defaultInstance = defaultInstance;
	}


	protected M beforeExec(IMessage msg) throws Exception{
		return (M) defaultInstance.newBuilderForType().mergeFrom(msg.getBody()).build();
	}


	public abstract void exec(T player,M msg);
}
