package com.designPattern.viewer;

import java.util.Map;

public interface StatViewer {
    void output(Map requestStats, long startTimeInMillis, long endTimeInMills);
}
