package com.kael.surf.muti;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class AppQueue {
	
	private LinkedBlockingQueue<AppTask> tasks;
	
	private AtomicBoolean running;
	private AppExecutors appExecutors;

	public AppQueue(AppExecutors appExecutors) {
		this.appExecutors = appExecutors;
		running = new AtomicBoolean(false);
		tasks = new LinkedBlockingQueue<AppTask>();
	}
	
	
	public void checkin(AppTask appTask){
		this.tasks.offer(appTask);
		if(this.running.compareAndSet(false, true)){
			execute();
		}
	}

	public void checkinDelayTask(DelayAppTask appTask){
		appExecutors.checkinDelayTask(appTask);
	}
	
	public void checkout(){
		tasks.poll();
		execute();
	}

	private void execute(){
		AppTask task = tasks.peek();
		if(task != null){
			appExecutors.execute(task);
		}else {
			running.set(false);
			task = tasks.peek();
			if(task != null && running.compareAndSet(false, true)){
				appExecutors.execute(task);
			}
		}
	}
}
