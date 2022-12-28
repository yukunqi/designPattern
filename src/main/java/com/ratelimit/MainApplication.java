package com.ratelimit;

import com.ratelimit.ratelimiter.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2022-12-28 11:27
 **/
public class MainApplication {
    private static final Logger log = LoggerFactory.getLogger(MainApplication.class);

    public static void main(String[] args) {
        RateLimiter rateLimiter = new RateLimiter();

        try {
            boolean access = rateLimiter.limit("msg-center", "/msg/v1/sms/push");
            System.out.println(access);
        } catch (InterruptedException interruptedException) {
            log.info("interrupted exception");
        }

    }
}
