package com.designPattern.reporter;

import com.designPattern.Aggregator;
import com.designPattern.storage.MetricsStorage;
import com.designPattern.storage.RedisMetricsStorage;
import com.designPattern.viewer.StatViewer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class EmailReporter extends AbstractReporter {

    private static final Long DAY_HOURS_IN_SECONDS = 86400L;

    public EmailReporter(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer, ScheduledExecutorService executor) {
        super(metricsStorage, aggregator, viewer, executor);
    }


    public void startDailyReport() {
        long durationInMillis = DAY_HOURS_IN_SECONDS * 1000;
        long endTimeInMillis = System.currentTimeMillis();

        this.scheduleReport(endTimeInMillis,durationInMillis,DAY_HOURS_IN_SECONDS);
    }

    public static EmailReporterBuilder builder(){
        return new EmailReporterBuilder();
    }

    public static EmailReporterBuilder defaultConfigurationBuilder(){
        return EmailReporterBuilder.defaultConfiguration();
    }

    public static class EmailReporterBuilder{
        private MetricsStorage metricsStorage;
        private Aggregator aggregator;
        private ScheduledExecutorService executor;
        private StatViewer statViewer;

        private EmailReporterBuilder() {
        }

        public static EmailReporterBuilder defaultConfiguration(){
            EmailReporterBuilder emailReporterBuilder = new EmailReporterBuilder();
            emailReporterBuilder.metricsStorage = new RedisMetricsStorage();
            emailReporterBuilder.aggregator = new Aggregator();
            emailReporterBuilder.executor = Executors.newSingleThreadScheduledExecutor();
            return emailReporterBuilder;
        }

        public EmailReporterBuilder statViewer(StatViewer statViewer){
            this.statViewer = statViewer;
            return this;
        }

        public EmailReporter build(){
            return new EmailReporter(this.metricsStorage,this.aggregator,this.statViewer,this.executor);
        }
    }
}
