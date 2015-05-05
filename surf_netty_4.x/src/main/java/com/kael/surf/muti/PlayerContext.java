package com.kael.surf.muti;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.kael.surf.net.AppPlayer;
import com.kael.surf.net.Cmd;
import com.kael.surf.net.ICommand;

public class PlayerContext {
	
	private static final PlayerContext instance = new PlayerContext();
	
	public static PlayerContext get(){
		return instance;
	}
	
	private AppExecutors appExecutors;
	
	private PlayerContext(){
		appExecutors = new AppExecutors("net");
	}

	public AppQueue getAppQueue(){
		return appExecutors.getAppQueue();
	}
	
	
	private Map<Short, ICommand> cmdMapper = new HashMap<Short, ICommand>();
	
	public void init(final Set<Class> clazzes){
		for (Class c : clazzes) {
			if(c.isAnnotationPresent(Cmd.class)){
				Cmd cmd = (Cmd) c.getAnnotation(Cmd.class);
				
				try {
					cmdMapper.put(cmd.code(),((ICommand) c.newInstance()));
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
		}
	}
	
	public ICommand getByCode(short code){
		return cmdMapper.get(code);
	}
	
	private ConcurrentHashMap<Integer, AppPlayer> onlinePlayers = new ConcurrentHashMap<Integer, AppPlayer>();
	
	public boolean addPlayer(AppPlayer appPlayer){
		return onlinePlayers.putIfAbsent(appPlayer.getPlayerId(), appPlayer) == null;
	}

	public boolean removePlayer(AppPlayer appPlayer){
		return onlinePlayers.remove(appPlayer.getPlayerId()) != null;
	}
	
	public ConcurrentHashMap<Integer, AppPlayer> getAllPlayers(){
		return onlinePlayers;
	}
}
