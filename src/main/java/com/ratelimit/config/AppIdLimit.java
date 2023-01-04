package com.ratelimit.config;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2022-12-27 20:54
 **/
public class AppIdLimit {
    private String appId;
    private Map<String,ApiLimit> limitMap;

    public AppIdLimit(String appId) {
        this(appId,new LinkedHashMap<>());
    }

    public AppIdLimit(String appId, Map<String, ApiLimit> limitMap) {
        this.appId = appId;
        this.limitMap = limitMap;
    }

    public String getAppId() {
        return appId;
    }

    public Map<String, ApiLimit> getLimitMap() {
        return limitMap;
    }

    //优化点 可以增加 动态的增加、删除的方法 用于 动态的增删 限流规则
    public void addApiLimit(String api,ApiLimit apiLimit){
        limitMap.putIfAbsent(api,apiLimit);
    }

    public void removeApiLimit(String api){
        limitMap.remove(api);
    }
}
