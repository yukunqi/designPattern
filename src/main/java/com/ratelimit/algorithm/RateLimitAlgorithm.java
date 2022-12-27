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
public class RateLimitAlgorithm {
    private Stopwatch stopwatch;
    private AtomicInteger currentCount = new AtomicInteger(0);
    private int limit;
    private int timeUnit;
    private Lock lock = new ReentrantLock();
    private static final int DEFAULT_LOCK_TIME_OUT = 1;

    public RateLimitAlgorithm(int limit,int timeUnit) {
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

    public boolean tryAndRequire() throws InterruptedException {
        if (currentCount.incrementAndGet() <= limit){
            return true;
        }

        //todo 优化点3 直接再次竞争锁 避免抛出异常 多个线程竞争失败了 可以再次竞争 优化 获取锁失败场景下 限流抛出异常 尝试多几次即可
        while (true){

            if (currentCount.get() < limit){
                return true;
            }

            if (lock.tryLock(DEFAULT_LOCK_TIME_OUT, TimeUnit.MILLISECONDS)){
                try {
                    int updated;
                    if (stopwatch.elapsed(TimeUnit.SECONDS) > timeUnit){
                        stopwatch.reset();
                        currentCount.set(0);
                        updated = currentCount.incrementAndGet();
                    }else {
                        updated = currentCount.get();
                    }

                    return updated <= limit;
                }finally {
                    lock.unlock();
                }
            }
        }
    }
}
