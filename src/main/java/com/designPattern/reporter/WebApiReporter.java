package com.designPattern.reporter;

import com.designPattern.Aggregator;
import com.designPattern.RequestInfo;
import com.designPattern.RequestStat;
import com.designPattern.storage.MetricsStorage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 提供给外部调用的API接口获取数据
 */
public class WebApiReporter{

    private MetricsStorage metricsStorage;
    private Aggregator aggregator;

    public WebApiReporter(MetricsStorage metricsStorage, Aggregator aggregator) {
        this.metricsStorage = metricsStorage;
        this.aggregator = aggregator;
    }

    public Map<String, RequestStat> reportByApi(String apiName, long startTimeInMillis, long endTimeInMillis){
        List<RequestInfo> requestInfoList = metricsStorage.getRequestInfos(apiName, startTimeInMillis, endTimeInMillis);

        Map<String,List<RequestInfo>> requestStat = new HashMap<>();
        requestStat.put(apiName,requestInfoList);

        return aggregator.aggregate(requestStat, endTimeInMillis - startTimeInMillis);
    }

    public Map<String,RequestStat> reportAll(long startTimeInMillis, long endTimeInMillis){

        Map<String, List<RequestInfo>> requestInfos = metricsStorage.getRequestInfos(startTimeInMillis, endTimeInMillis);

        return aggregator.aggregate(requestInfos,endTimeInMillis - startTimeInMillis);
    }
}
