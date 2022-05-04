package com.designPattern.reporter;

import com.designPattern.Aggregator;
import com.designPattern.RequestInfo;
import com.designPattern.RequestStat;
import com.designPattern.storage.MetricsStorage;
import com.designPattern.viewer.StatViewer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractReporter {
    private MetricsStorage metricsStorage;
    private Aggregator aggregator;
    private StatViewer viewer;

    private static final long TEN_MINUTES = 10 * 60 * 1000;

    public AbstractReporter(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer) {
        this.metricsStorage = metricsStorage;
        this.aggregator = aggregator;
        this.viewer = viewer;
    }

    protected void doReport(long startTimeInMillis, long endTimeInMillis) {
        long durationInMillis = endTimeInMillis - startTimeInMillis;

        long start_pos = startTimeInMillis;
        Map<String,List<RequestStat>> requestStatListMap = new HashMap<>();

        while (start_pos < endTimeInMillis){
            long end_pos = start_pos + TEN_MINUTES;
            if (end_pos > endTimeInMillis){
                end_pos = endTimeInMillis;
            }

            Map<String, List<RequestInfo>> requestInfos = metricsStorage.getRequestInfos(start_pos, end_pos);
            Map<String, RequestStat> requestStats = aggregator.aggregate(requestInfos, TEN_MINUTES);

            addStat(requestStatListMap,requestStats);

            start_pos = end_pos;
        }


        Map<String, RequestStat> requestStatMap = aggregateFromSegment(requestStatListMap, durationInMillis);
        viewer.output(requestStatMap, startTimeInMillis, endTimeInMillis);
    }

    private void addStat(Map<String,List<RequestStat>> requestStatListMap,Map<String, RequestStat> requestStatMap){
        for (Map.Entry<String,RequestStat> entry : requestStatMap.entrySet()){
            String apiName = entry.getKey();
            RequestStat requestStat = entry.getValue();

            List<RequestStat> statList = requestStatListMap.computeIfAbsent(apiName, (key) -> {
                List<RequestStat> requestStatList = new ArrayList<>();
                requestStatList.add(requestStat);
                return requestStatList;
            });

            statList.add(requestStat);
        }
    }

    private Map<String, RequestStat> aggregateFromSegment(Map<String,List<RequestStat>> requestStatListMap,long durationMills){

        Map<String,RequestStat> requestStatMap = new HashMap<>();

        for (Map.Entry<String,List<RequestStat>> entry : requestStatListMap.entrySet()){
            String apiName = entry.getKey();
            List<RequestStat> requestStatList = entry.getValue();

            double maxResponse = 0;
            double minResponse = 0;
            double totalResponse = 0;
            long count = 0;

            for (RequestStat requestStat : requestStatList){
                maxResponse = Math.max(maxResponse,requestStat.getMaxResponseTime());
                minResponse = Math.min(minResponse,requestStat.getMinResponseTime());
                count += requestStat.getCount();
                totalResponse += requestStat.getCount() * requestStat.getAvgResponseTime();
            }

            double avgResponse = totalResponse / count;
            long tps =  count / (durationMills * 1000);

            RequestStat fromSegment = new RequestStat();
            fromSegment.setCount(count);
            fromSegment.setMaxResponseTime(maxResponse);
            fromSegment.setMinResponseTime(minResponse);
            fromSegment.setAvgResponseTime(avgResponse);
            fromSegment.setTps(tps);

            //todo p999 p99 的计算 需要使用排序算法来实现

            requestStatMap.put(apiName,fromSegment);
        }

        return requestStatMap;
    }

}
