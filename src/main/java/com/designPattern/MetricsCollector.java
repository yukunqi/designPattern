package com.designPattern;

import com.designPattern.storage.MetricsStorage;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.Executors;

public class MetricsCollector {
    private MetricsStorage metricsStorage;
    private EventBus eventBus;
    private static final int DEFAULT_CORE_SIZE = 1;


    public MetricsCollector(MetricsStorage metricsStorage) {
        this(metricsStorage,DEFAULT_CORE_SIZE);
    }

    public MetricsCollector(MetricsStorage metricsStorage,int coreSize) {
        this.metricsStorage = metricsStorage;
        this.eventBus = new AsyncEventBus(Executors.newFixedThreadPool(coreSize));
        this.eventBus.register(new IncomingRequestInfoListener());
    }


    public void recordRequest(RequestInfo requestInfo) {
        if (requestInfo == null || StringUtils.isBlank(requestInfo.getApiName())) {
            return;
        }

        //生产者 生产requestInfo
        eventBus.post(requestInfo);
    }

    public class IncomingRequestInfoListener{

        @Subscribe
        public void saveRequestInfo(RequestInfo requestInfo){
            //消费 请求API信息对象
            metricsStorage.saveRequestInfo(requestInfo);
        }
    }
}