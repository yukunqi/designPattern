package com.designPattern.reporter;

import com.designPattern.Aggregator;
import com.designPattern.RequestInfo;
import com.designPattern.RequestStat;
import com.designPattern.storage.MetricsStorage;
import com.designPattern.viewer.StatViewer;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class AbstractReporter {
    private MetricsStorage metricsStorage;
    private Aggregator aggregator;
    private StatViewer viewer;
    private ScheduledExecutorService executor;


    public AbstractReporter(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer, ScheduledExecutorService executor) {
        this.metricsStorage = metricsStorage;
        this.aggregator = aggregator;
        this.viewer = viewer;
        this.executor = executor;
    }



    private void doReport(long endTimeInMillis, long durationInMillis) {
        long startTimeInMillis = endTimeInMillis - durationInMillis;
        Map<String, List<RequestInfo>> requestInfos = metricsStorage.getRequestInfos(startTimeInMillis, endTimeInMillis);
        Map<String, RequestStat> requestStats = aggregator.aggregate(requestInfos, durationInMillis);
        viewer.output(requestStats, startTimeInMillis, endTimeInMillis);
    }

    void scheduleReport(long endTimeInMillis, long durationInMillis, long periodInSeconds){
        executor.scheduleAtFixedRate(() -> doReport(endTimeInMillis,durationInMillis),0L,periodInSeconds, TimeUnit.SECONDS);
    }
}
