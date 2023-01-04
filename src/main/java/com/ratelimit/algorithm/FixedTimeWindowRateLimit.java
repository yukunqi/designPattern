package com.ratelimit.algorithm;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2022-12-27 15:11
 **/
//优化点 类的名称可以变为具体的实现名称 比如 FixedTimeWindowRateLimit
public class FixedTimeWindowRateLimit implements RateLimitAlgorithm {
    private Stopwatch stopwatch;
    private AtomicInteger currentCount = new AtomicInteger(0);
    private int limit;
    private int timeUnit;
    private Lock lock = new ReentrantLock();
    private static final int DEFAULT_LOCK_TIME_OUT = 20;

    public FixedTimeWindowRateLimit(int limit, int timeUnit) {
        this.limit = limit;
        this.timeUnit = timeUnit;
        this.stopwatch = Stopwatch.createStarted();
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setTimeUnit(int timeUnit) {
        this.timeUnit = timeUnit;
    }

    @Override
    public boolean tryAndRequire() throws InterruptedException {
        if (currentCount.incrementAndGet() <= limit) {
            return true;
        }

        if (lock.tryLock(DEFAULT_LOCK_TIME_OUT, TimeUnit.MILLISECONDS)) {
            try {
                int updated;
                if (stopwatch.elapsed(TimeUnit.SECONDS) > timeUnit) {
                    stopwatch.reset();
                    //remind by https://time.geekbang.org/column/article/243961 comment
                    stopwatch.start();
                    currentCount.set(0);
                    updated = currentCount.incrementAndGet();
                } else {
                    updated = currentCount.get();
                }

                return updated <= limit;
            } finally {
                lock.unlock();
            }
        }else {
            throw new RuntimeException("wait for get rate limit lock timed out");
        }

    }
}
