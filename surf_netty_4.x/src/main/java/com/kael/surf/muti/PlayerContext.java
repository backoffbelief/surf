package com.kael.surf.muti;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.google.protobuf.GeneratedMessage;
import com.kael.surf.net.AbstractCommand;
import com.kael.surf.net.AppPlayer;
import com.kael.surf.net.Cmd;
import com.kael.surf.net.Constants;
import com.kael.surf.net.ICommand;
import com.kael.surf.net.Resp;

public class PlayerContext {

	private static final PlayerContext instance = new PlayerContext();

	public static PlayerContext get() {
		return instance;
	}

	private AppExecutors appExecutors;

	private PlayerContext() {
		appExecutors = new AppExecutors("net");
	}

	public AppQueue getAppQueue() {
		return appExecutors.getAppQueue();
	}

	private Map<Short, ICommand> cmdMapper = new HashMap<Short, ICommand>();
	
	private Map<String, Short> respMapper = new HashMap<String, Short>();

	public void init(final Set<Class<?>> clazzes) throws Exception{
		for (Class<?> c : clazzes) {
			if (c.isAnnotationPresent(Cmd.class)) {
				Cmd cmd =  c.getAnnotation(Cmd.class);
				AbstractCommand  command = (AbstractCommand) c.newInstance();
				 GeneratedMessage proto = (GeneratedMessage) Class.forName(cmd.protoName()).getMethod("getDefaultInstance", null)
		                    .invoke(null, null);
				 if(proto == null){
					 throw new RuntimeException("not find class:"+cmd.protoName());
				 }
				command.setDefaultInstance(proto);
				cmdMapper.put(cmd.code(), command);
			}
		}

		Field[] fs = Constants.constants.getClass().getDeclaredFields();
		for (Field field : fs) {
			if (field.isAnnotationPresent(Resp.class)) {
				Resp resp = field.getAnnotation(Resp.class);
//				System.out.println(field.getShort(Constants.constants) + "==" + field.getName()
//						+ "," + resp.protoName());
				respMapper.put(resp.protoName(), field.getShort(Constants.constants));
				
			}
		}
	}

	public ICommand getByCode(short code) {
		return cmdMapper.get(code);
	}

	public Short getCodeByProtoName(String protoName) {
		return respMapper.get(protoName);
	}

	private ConcurrentHashMap<Integer, AppPlayer> onlinePlayers = new ConcurrentHashMap<Integer, AppPlayer>();

	public boolean addPlayer(AppPlayer appPlayer) {
		return onlinePlayers.putIfAbsent(appPlayer.getPlayerId(), appPlayer) == null;
	}

	public boolean removePlayer(AppPlayer appPlayer) {
		return onlinePlayers.remove(appPlayer.getPlayerId()) != null;
	}

	public ConcurrentHashMap<Integer, AppPlayer> getAllPlayers() {
		return onlinePlayers;
	}
}
