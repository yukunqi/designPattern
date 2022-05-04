package com.designPattern.format;

import com.designPattern.RequestStat;
import com.designPattern.exception.StatFormatException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class JsonStatFormat implements StatFormat{

    private ObjectMapper objectMapper = new ObjectMapper();

    public JsonStatFormat() {
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String format(Map<String, RequestStat> requestStatMap) {
        try {
            return objectMapper.writeValueAsString(requestStatMap);
        } catch (JsonProcessingException e) {
            throw new StatFormatException("format into json pattern using jackson error",e);
        }
    }
}
