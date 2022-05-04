package com.designPattern.format;

import com.designPattern.RequestStat;
import com.designPattern.exception.StatFormatException;

import java.util.Map;

public interface StatFormat {


    /**
     * format request stat infos into string
     * @param requestStatMap
     * @return
     * @throws StatFormatException any exception during format will throw StatFormatException
     */
    String format(Map<String, RequestStat> requestStatMap) throws StatFormatException;
}
