package com.ioc.bean;

public class RedisCounter {
    private String ipAddress;
    private Integer port;

    public RedisCounter(String ipAddress, Integer port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }

    public void count(){
        System.out.println("counting in RedisCounter...");
    }
}
