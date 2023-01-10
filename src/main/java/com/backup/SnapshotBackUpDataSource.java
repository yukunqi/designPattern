package com.backup;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2023-01-05 23:00
 **/
public class SnapshotBackUpDataSource {

    private BlockingQueue<Snapshot> snapshotQueue;
    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
    private final String threadNamePrefix = "increase-backup-threadPool";

    public SnapshotBackUpDataSource() {
        this.snapshotQueue = new LinkedBlockingQueue<>();
        scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1,new CustomNamedThreadFactory(threadNamePrefix));
    }

    public void backup(Snapshot snapshot){
        this.snapshotQueue.add(snapshot);
    }

    public void revert(){

    }
}
