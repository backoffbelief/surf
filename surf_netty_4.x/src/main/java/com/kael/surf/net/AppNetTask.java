package com.kael.surf.net;

import com.google.protobuf.GeneratedMessage;
import com.kael.surf.muti.AppTask;

public class AppNetTask<T extends AppPlayer,M extends GeneratedMessage,N extends AbstractCommand<T,M>> extends AppTask {
	private final T player;
	private final N cmd;
	private final M msg;
	
	public AppNetTask(T player, N ac,M msg) {
		super(player.getAppQueue());
		this.player = player;
		this.cmd = ac;
		this.msg = msg;
	}

	@Override
	protected void exec() {
		cmd.exec(player, msg);
	}

}
