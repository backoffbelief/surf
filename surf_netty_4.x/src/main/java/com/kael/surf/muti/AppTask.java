package com.kael.surf.muti;

public abstract class AppTask implements Runnable {
	protected AppQueue appQueue;
	protected long createTime;

	public AppTask(AppQueue appQueue) {
		super();
		this.appQueue = appQueue;
		this.createTime = System.currentTimeMillis();
	}

	public void checkIn() {
		appQueue.checkin(this);
	}
	
	@Override
	public void run() {

		try {
			exec();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.appQueue.checkout();
		}
	}

	protected abstract void exec();
}
