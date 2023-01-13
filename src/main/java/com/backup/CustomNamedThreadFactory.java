package com.backup;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2023-01-10 20:33
 **/
public class CustomNamedThreadFactory implements ThreadFactory {
    private final ThreadGroup group;
    private String threadNamePrefix;
    private final AtomicInteger threadNumber = new AtomicInteger(1);

    public CustomNamedThreadFactory(String threadNamePrefix) {
        this.threadNamePrefix = threadNamePrefix;
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() :
                Thread.currentThread().getThreadGroup();
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r,
                threadNamePrefix + "-" + threadNumber.getAndIncrement(),
                0);

        if (t.isDaemon()) {
            t.setDaemon(false);
        }

        if (t.getPriority() != Thread.NORM_PRIORITY){
            t.setPriority(Thread.NORM_PRIORITY);
        }

        return t;
    }



}
