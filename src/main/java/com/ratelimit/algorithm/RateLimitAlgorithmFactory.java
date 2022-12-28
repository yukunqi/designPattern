package com.ratelimit.algorithm;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2022-12-28 12:00
 **/
public class RateLimitAlgorithmFactory {
    public static final String FIXED_WINDOW = "FixedTimeWindow";
    private final String createType;

    public RateLimitAlgorithmFactory(String createType) {
        this.createType = createType;
    }

    public RateLimitAlgorithm createRateLimitAlgorithm(int limit , int timeUnit){
        switch (createType){
            case FIXED_WINDOW:{
                return new FixedTimeWindowRateLimit(limit, timeUnit);
            }
            default:{
                throw new UnsupportedOperationException();
            }
        }
    }
}
