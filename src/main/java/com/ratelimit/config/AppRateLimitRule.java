package com.ratelimit.config;

import java.util.Map;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2022-12-27 15:18
 **/
public class AppRateLimitRule {
    private Map<String,AppIdLimit> root;

    public AppRateLimitRule(RuleConfig ruleConfig) {
        this.root = ruleConfig.getAppIdLimitLinkedHashMap();
    }

    public ApiLimit getApiLimit(String appId,String url){
        AppIdLimit appIdLimit = root.get(appId);
        if (appIdLimit != null){
            return appIdLimit.getLimitMap().get(url);
        }
        return null;
    }
}
