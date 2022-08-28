package com.ioc.bean;

public class RateLimiter {
    private RedisCounter redisCounter;

    public RateLimiter(RedisCounter redisCounter) {
        this.redisCounter = redisCounter;
    }

    public void test() {
        System.out.println("this is RateLimiter and we are about to execute dependency object");
        redisCounter.count();
    }
}
