package com.designPattern;

import com.designPattern.reporter.ConsoleReporter;
import com.designPattern.reporter.EmailReporter;
import com.designPattern.viewer.ConsoleViewer;
import com.designPattern.viewer.EmailViewer;

public class ReportMainApplication {
    /**
     * 性能计数器项目完善
     * @param args
     */
    public static void main(String[] args) {
        //console report build
        ConsoleViewer consoleViewer = new ConsoleViewer();
        ConsoleReporter consoleReporter = ConsoleReporter
                                            .defaultConfigurationBuilder()
                                            .statViewer(consoleViewer)
                                            .build();

        consoleReporter.startRepeatedReport(60,60);

        //email report build
        EmailViewer emailViewer = new EmailViewer();
        emailViewer.addToAddress("someBody@gmail.com");
        EmailReporter emailReporter = EmailReporter
                                                .defaultConfigurationBuilder()
                                                .statViewer(emailViewer)
                                                .build();
        emailReporter.startDailyReport();
    }
}
