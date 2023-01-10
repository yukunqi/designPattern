package com.backup;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2023-01-10 20:33
 **/
public class CustomNamedThreadFactory implements ThreadFactory {

    private String threadNamePrefix;
    private final AtomicInteger threadNumber = new AtomicInteger(1);

    public CustomNamedThreadFactory(String threadNamePrefix) {
        this.threadNamePrefix = threadNamePrefix;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r,threadNamePrefix + "-thread-" + threadNumber.getAndIncrement());
    }
}
