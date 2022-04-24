package com.designPattern.reporter;

import com.designPattern.Aggregator;
import com.designPattern.RequestInfo;
import com.designPattern.RequestStat;
import com.designPattern.storage.MetricsStorage;
import com.designPattern.viewer.StatViewer;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;

public abstract class AbstractReporter {
    private MetricsStorage metricsStorage;
    private Aggregator aggregator;
    private StatViewer viewer;
    protected ScheduledExecutorService executor;


    public AbstractReporter(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer, ScheduledExecutorService executor) {
        this.metricsStorage = metricsStorage;
        this.aggregator = aggregator;
        this.viewer = viewer;
        this.executor = executor;
    }


    protected void doReport(long startTimeInMillis, long endTimeInMillis) {
        long durationInMillis = endTimeInMillis - startTimeInMillis;
        Map<String, List<RequestInfo>> requestInfos = metricsStorage.getRequestInfos(startTimeInMillis, endTimeInMillis);
        Map<String, RequestStat> requestStats = aggregator.aggregate(requestInfos, durationInMillis);
        viewer.output(requestStats, startTimeInMillis, endTimeInMillis);
    }

}
