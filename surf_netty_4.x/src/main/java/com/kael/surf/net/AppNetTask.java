package com.kael.surf.net;

import com.kael.surf.muti.AppTask;

public class AppNetTask<T extends AppPlayer,N extends AbstractCommand<T>> extends AppTask {
	private final T player;
	private final N cmd;
	private final IMessage msg;
	
	public AppNetTask(T player, N ac,IMessage msg) {
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
