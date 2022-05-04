package com.designPattern.reporter;

import com.designPattern.Aggregator;
import com.designPattern.RequestInfo;
import com.designPattern.RequestStat;
import com.designPattern.storage.MetricsStorage;
import com.designPattern.viewer.StatViewer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractReporter {
    private MetricsStorage metricsStorage;
    private Aggregator aggregator;
    private StatViewer viewer;



    public AbstractReporter(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer) {
        this.metricsStorage = metricsStorage;
        this.aggregator = aggregator;
        this.viewer = viewer;
    }


    protected void doReport(long startTimeInMillis, long endTimeInMillis) {
        long durationInMillis = endTimeInMillis - startTimeInMillis;
        Map<String, List<RequestInfo>> requestInfos = metricsStorage.getRequestInfos(startTimeInMillis, endTimeInMillis);
        Map<String, RequestStat> requestStats = aggregator.aggregate(requestInfos, durationInMillis);
        viewer.output(requestStats, startTimeInMillis, endTimeInMillis);
    }

    protected void doReportByApi(String apiName,long startTimeInMillis, long endTimeInMillis) {
        long durationInMillis = endTimeInMillis - startTimeInMillis;
        List<RequestInfo> requestInfos = metricsStorage.getRequestInfos(apiName, startTimeInMillis, endTimeInMillis);

        Map<String,List<RequestInfo>> map = new HashMap<>();
        map.put(apiName,requestInfos);

        Map<String, RequestStat> requestStats = aggregator.aggregate(map, durationInMillis);
        viewer.output(requestStats, startTimeInMillis, endTimeInMillis);
    }

}
