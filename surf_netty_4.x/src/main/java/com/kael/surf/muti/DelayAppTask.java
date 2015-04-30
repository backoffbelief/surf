package com.kael.surf.muti;

public abstract class DelayAppTask extends AppTask {
	private long execTime;
	private volatile boolean cancel;

	public DelayAppTask(AppQueue appQueue, long currTime, int delay) {
		super(appQueue);
		calExecTime(currTime, delay);
	}

	public DelayAppTask(AppQueue appQueue, int delay) {
		this(appQueue, System.currentTimeMillis(), delay);
	}

	private void calExecTime(long currTime, int delay) {
		if (delay > 0) {
			this.execTime = currTime + delay;
		} else {
			this.execTime = 0;
		}
	}

	public boolean isCancel() {
		return cancel;
	}

	public void cancel() {
		this.cancel = true;
	}

	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}

	@Override
	public void run() {
		if (cancel)
			return;
		super.run();
	}

	@Override
	public void checkIn() {
		if (execTime == 0) {
			super.checkIn();
		} else {
			appQueue.checkinDelayTask(this);
		}
	}

	public boolean tryExec(long currTime) {
		if (cancel)
			return true;
		if (execTime <= currTime) {
			super.checkIn();
			createTime = currTime;
			return true;
		}
		return false;
	}

}
