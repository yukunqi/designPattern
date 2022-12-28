package com.ratelimit.config;

import java.util.Map;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2022-12-27 15:17
 **/
public class RuleConfig {
    private Map<String,AppIdLimit> appIdLimitLinkedHashMap;

    public RuleConfig(Map<String, AppIdLimit> appIdLimitLinkedHashMap) {
        this.appIdLimitLinkedHashMap = appIdLimitLinkedHashMap;
    }

    public Map<String, AppIdLimit> getAppIdLimitLinkedHashMap() {
        return appIdLimitLinkedHashMap;
    }
}
