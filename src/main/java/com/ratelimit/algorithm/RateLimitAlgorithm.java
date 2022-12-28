package com.ratelimit.algorithm;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2022-12-28 11:44
 **/
public interface RateLimitAlgorithm {

    boolean tryAndRequire() throws InterruptedException;
}
