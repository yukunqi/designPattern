package com.designPattern.viewer;

import java.util.Map;

public class ConsoleViewer implements StatViewer {
    @Override
    public void output(Map requestStats, long startTimeInMillis, long endTimeInMills) {
        System.out.println("Time Span: [" + startTimeInMillis + ", " + endTimeInMills + "]");
        System.out.println(requestStats);
    }
}
