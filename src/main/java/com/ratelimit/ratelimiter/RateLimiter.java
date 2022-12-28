package com.ratelimit.ratelimiter;

import com.ratelimit.algorithm.RateLimitAlgorithm;
import com.ratelimit.algorithm.RateLimitAlgorithmFactory;
import com.ratelimit.config.ApiLimit;
import com.ratelimit.config.AppRateLimitRule;
import com.ratelimit.config.RuleConfig;
import com.ratelimit.datasource.ClassPathFileRuleConfigDataSource;
import com.ratelimit.datasource.RuleConfigDataSource;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2022-12-27 17:25
 **/
public class RateLimiter {

    private AppRateLimitRule appRateLimitRule;
    private RateLimitAlgorithmFactory rateLimitAlgorithmFactory;
    private Map<String, RateLimitAlgorithm> rateLimitAlgorithmMap = new ConcurrentHashMap<>();

    public RateLimiter(RateLimitAlgorithmFactory rateLimitAlgorithmFactory) {
        RuleConfigDataSource ruleConfigDataSource = new ClassPathFileRuleConfigDataSource();
        RuleConfig ruleConfig = ruleConfigDataSource.load();
        this.rateLimitAlgorithmFactory = rateLimitAlgorithmFactory;
        this.appRateLimitRule = new AppRateLimitRule(ruleConfig);
    }

    public boolean limit(String appId, String url) throws InterruptedException {
        ApiLimit apiLimit = appRateLimitRule.getApiLimit(appId, url);

        String key = appId + apiLimit.getUrl();
        RateLimitAlgorithm oldRateLimitAlgorithm = rateLimitAlgorithmMap.get(key);
        if (oldRateLimitAlgorithm == null){
            //todo 优化点 可以由工厂来构造实例化 具体的 限流算法 不硬编码new 出固定时间窗口的类
            RateLimitAlgorithm newRateLimitAlgorithm = rateLimitAlgorithmFactory.createRateLimitAlgorithm(apiLimit.getLimit(),apiLimit.getUnit());
            oldRateLimitAlgorithm = rateLimitAlgorithmMap.putIfAbsent(key, newRateLimitAlgorithm);
            if (oldRateLimitAlgorithm == null){
                //map associated with newRateLimitAlgorithm
                oldRateLimitAlgorithm = newRateLimitAlgorithm;
            }
        }

        return oldRateLimitAlgorithm.tryAndRequire();
    }
}
