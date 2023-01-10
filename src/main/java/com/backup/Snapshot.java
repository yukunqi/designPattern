package com.backup;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2023-01-05 22:50
 **/
public class Snapshot {
    private final long createTimeStamp;
    private final Object data;
    private final String version;

    public Snapshot( Object data) {
        this.createTimeStamp = System.currentTimeMillis();
        this.data = data;
        this.version = String.valueOf(createTimeStamp);
    }

}
