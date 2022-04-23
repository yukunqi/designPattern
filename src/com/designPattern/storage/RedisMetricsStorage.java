package com.designPattern.storage;

import com.designPattern.RequestInfo;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class RedisMetricsStorage implements MetricsStorage {

    @Override
    public void saveRequestInfo(RequestInfo requestInfo) {
        System.out.println(String.format("save request info into redis requestInfo: %s",requestInfo.toString()));
    }

    @Override
    public List<RequestInfo> getRequestInfos(String apiName, long startTimeInMillis, long endTimeInMillis) {
        System.out.println(String.format("getRequestInfos from %s %d %d",apiName,startTimeInMillis,endTimeInMillis));
        return Collections.emptyList();
    }

    @Override
    public Map<String, List<RequestInfo>> getRequestInfos(long startTimeInMillis, long endTimeInMillis) {
        System.out.println(String.format("getRequestInfos from %d %d",startTimeInMillis,endTimeInMillis));
        return Collections.emptyMap();
    }
}
