package com.designPattern.reporter;

import com.designPattern.Aggregator;
import com.designPattern.storage.MetricsStorage;
import com.designPattern.storage.RedisMetricsStorage;
import com.designPattern.viewer.StatViewer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ConsoleReporter extends AbstractReporter {


    public ConsoleReporter(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer, ScheduledExecutorService executor) {
        super(metricsStorage, aggregator, viewer, executor);
    }


    public void startRepeatedReport(long periodInSeconds, long durationInSeconds) {
        long durationInMillis = durationInSeconds * 1000;
        long endTimeInMillis = System.currentTimeMillis();
        long startTimeInMillis = endTimeInMillis - durationInMillis;
        executor.scheduleAtFixedRate(() -> doReport(startTimeInMillis,endTimeInMillis),0L,periodInSeconds, TimeUnit.SECONDS);

    }


    public static ConsoleReporter.ConsoleReportBuilder builder(){
        return new ConsoleReporter.ConsoleReportBuilder();
    }

    public static ConsoleReporter.ConsoleReportBuilder defaultConfigurationBuilder(){
        return ConsoleReporter.ConsoleReportBuilder.defaultConfiguration();
    }


    public static class ConsoleReportBuilder{
        private MetricsStorage metricsStorage;
        private Aggregator aggregator;
        private ScheduledExecutorService executor;
        private StatViewer statViewer;

        private ConsoleReportBuilder(){}

        private static ConsoleReporter.ConsoleReportBuilder defaultConfiguration(){
            ConsoleReporter.ConsoleReportBuilder emailReporterBuilder = new ConsoleReporter.ConsoleReportBuilder();
            emailReporterBuilder.metricsStorage = new RedisMetricsStorage();
            emailReporterBuilder.aggregator = new Aggregator();
            emailReporterBuilder.executor = Executors.newSingleThreadScheduledExecutor();
            return emailReporterBuilder;
        }

        public ConsoleReporter.ConsoleReportBuilder statViewer(StatViewer statViewer){
            this.statViewer = statViewer;
            return this;
        }

        public ConsoleReporter build(){
            return new ConsoleReporter(this.metricsStorage,this.aggregator,this.statViewer,this.executor);
        }
    }
}

