package com.kael.surf.net;

public abstract class AbstractCommand<T extends AppPlayer> implements ICommand {

	public abstract void exec(T player,IMessage msg);
}
