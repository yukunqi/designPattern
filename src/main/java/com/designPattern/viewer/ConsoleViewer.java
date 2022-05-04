package com.designPattern.viewer;

import com.designPattern.format.JsonStatFormat;
import com.designPattern.format.StatFormat;

import java.util.Map;

public class ConsoleViewer implements StatViewer {

    private StatFormat statFormat = new JsonStatFormat();

    public void setStatFormat(StatFormat statFormat) {
        this.statFormat = statFormat;
    }

    @Override
    public void output(Map requestStats, long startTimeInMillis, long endTimeInMills) {
        System.out.println("Time Span: [" + startTimeInMillis + ", " + endTimeInMills + "]");
        String jsonString = statFormat.format(requestStats);
        System.out.println(jsonString);
    }
}
