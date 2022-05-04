package com.designPattern.format;

import com.designPattern.RequestStat;
import com.designPattern.exception.StatFormatException;

import java.util.Map;

public class EmailStatFormat implements StatFormat{


    @Override
    public String format(Map<String, RequestStat> requestStatMap) throws StatFormatException {
        return String.format("email format : %s",requestStatMap.toString());
    }
}
