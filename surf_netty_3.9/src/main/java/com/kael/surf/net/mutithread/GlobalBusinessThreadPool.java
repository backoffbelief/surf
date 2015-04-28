package com.kael.surf.net.mutithread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class GlobalBusinessThreadPool {

    private final ThreadPoolExecutor executor;

    /**
     * The synFight global business thread factory
     */
    static class SynFightGlobalBusinessThreadFactory implements ThreadFactory {
        final ThreadGroup group;

        final AtomicInteger threadNumber = new AtomicInteger(1);

        final String namePrefix;

        SynFightGlobalBusinessThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = "SynFightGlobalBusinessThreadPool" + "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }
    
    public GlobalBusinessThreadPool(int minThreadNum, int maxThreadNum) {
        this(minThreadNum, maxThreadNum, 1000);
    }
    
    public GlobalBusinessThreadPool(int minThreadNum, int maxThreadNum, int queueTaskNum) {
        executor = new ThreadPoolExecutor(minThreadNum, maxThreadNum, 30000L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(queueTaskNum), new SynFightGlobalBusinessThreadFactory());
    }

    public int getThreadNum() {
        return executor.getPoolSize();
    }

    public int getQueueNum() {
        return executor.getQueue().size();
    }

    public long getCompleteTaskNum() {
        return executor.getCompletedTaskCount();
    }

    public long getScheduleTaskNum() {
        return executor.getTaskCount();
    }

    public int getActiveThreadNum() {
        return executor.getActiveCount();
    }

    public int getMaxThreadNumInHistory() {
        return executor.getLargestPoolSize();
    }

    public void execute(Runnable command) {
        executor.execute(command);
    }
}
