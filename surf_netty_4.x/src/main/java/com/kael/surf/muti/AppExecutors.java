package com.kael.surf.muti;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AppExecutors {
	
	private ThreadPoolExecutor executor = null;
	
	private AppQueue appQueue;
	
	private LinkedBlockingQueue<Runnable> workQueue;

	private DelayCheckThread checkThread;
	
	private String appName;
	
	public AppExecutors(String appName) {
		workQueue = new LinkedBlockingQueue<Runnable>();
		
		executor = new ThreadPoolExecutor(1, 2, 5, TimeUnit.MINUTES, workQueue);
		appQueue = new AppQueue(this);
		this.appName = appName;
	}

	public void execute(Runnable task) {
		executor.execute(task);
	}

	public AppQueue getAppQueue() {
		return appQueue;
	}
	
	private synchronized void configureDelayThread(){
		if (checkThread == null) {
			checkThread = new DelayCheckThread(appName);
			checkThread.start();
		}
	}
	
	public void checkinDelayTask(DelayAppTask delayAppTask) {
		configureDelayThread();
		checkThread.checkin(delayAppTask);
		
	}
	
	public void stop(){
		if(checkThread != null){
			checkThread.stopMe();
		}
	}
	
	
	static class DelayCheckThread extends Thread{
		private static final int ZIZZ_TIME = 80;
		
		private ConcurrentLinkedDeque<DelayAppTask> queue ;
		
		private boolean isRunning;
		
		public DelayCheckThread(String prefix) {
            super(prefix + "-thread-dc");
            queue = new ConcurrentLinkedDeque<DelayAppTask>();
            isRunning = true;
            setPriority(Thread.MAX_PRIORITY); // 给予高优先级
        }

		public boolean isRunning() {
			return isRunning;
		}
		
		public void stopMe(){
			if(isRunning){
				isRunning = false;
			}
		}

		@Override
		public void run() {
			while(isRunning){
				try{
				long zizzTime = ZIZZ_TIME;
                if(!this.queue.isEmpty()) {
                    long start = System.currentTimeMillis();
                    int checked = checkActions();
                    long interval = System.currentTimeMillis() - start;
                    zizzTime -= interval;
                    if (interval > ZIZZ_TIME) {
                        System.out.println(getName() + " is spent too much time: " + interval + "ms, checked num = " + checked);
                    }
                }
                
                if (zizzTime > 0) {
                    TimeUnit.MILLISECONDS.sleep(zizzTime);
                }
            } catch (Exception e) {
//                logger.error(getName() + " Error. ", e);
			}
			}
		}
		
		 /**
         * 返回检查完成的Action数量
         **/
        public int checkActions() {
            DelayAppTask last = this.queue.peekLast();
            if(last == null) {
                return 0;
            }
            
            int checked = 0;
            DelayAppTask current = null;
            
            while((current = this.queue.removeFirst()) != null) {
                try {
                    long begin = System.currentTimeMillis();
                    if (!current.tryExec(begin)) {
                        checkin(current);
                    }
                    checked++;
                    long end = System.currentTimeMillis();
                    if (end - begin > ZIZZ_TIME) {
//                        logger.warn(current.toString() + " spent too much time. time :" + (end - begin));
                    }
                    if(current == last) {
                        break;
                    }
                } catch (Exception e) {
//                    logger.error("检测action delay 异常" + current.toString(), e);
                }
            }
            return checked;
        }
 
        /**
         * 添加Action到队列
         * @param delayAction
         */
        public void checkin(DelayAppTask delayAction) {
            queue.addLast(delayAction);
        }
	
	}


}
