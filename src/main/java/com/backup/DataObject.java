package com.backup;

import java.io.Closeable;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2023-01-05 23:10
 **/
public class DataObject implements Closeable {

    private final StringBuilder text;
    private SnapshotBackUpDataSource snapshotBackUpDataSource = new SnapshotBackUpDataSource();

    public DataObject() {
        text = new StringBuilder();
    }

    public DataObject(String content) {
        text = new StringBuilder(content);
    }

    public void initByVersion(long versionTimeStamp){
        String restore = snapshotBackUpDataSource.restore(versionTimeStamp);
        text.replace(0,text.length(),restore);
    }

    @Override
    public void close(){
        this.snapshotBackUpDataSource.stop();
    }

    public String getText() {
        return text.toString();
    }

    public void append(String input) {
        text.append(input);
        Snapshot snapshot = new Snapshot(Operator.Add,input);
        snapshotBackUpDataSource.backup(snapshot);
    }
}
