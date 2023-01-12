package com.backup;

import java.io.*;
import java.util.concurrent.*;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2023-01-05 23:00
 **/
public class SnapshotBackUpDataSource {

    private BlockingQueue<Snapshot> snapshotQueue;
    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
    private ScheduledThreadPoolExecutor fullBackupThreadPoolExecutor;
    private final String threadNamePrefix = "increase-backup-threadPool";
    private final String fullBackupThreadNamePrefix = "full-backup-threadPool";
    private int increaseDelay = 1;
    private int fullDelay = 5 * 60;
    private ScheduledFuture<?> increaseFuture;
    private ScheduledFuture<?> fullFuture;
    private String parentDir = "/Users/kunqi.yu/Downloads/snapshot";
    private String increaseBackupFileName = "snapshot-increase";
    private String fullBackupFileName = "snapshot-full";
    private File currentIncreaseFile;
    private File currentFullFile;
    private FileOutputStream currentIncreaseFileOutputStream;


    public SnapshotBackUpDataSource() {
        this.snapshotQueue = new LinkedBlockingQueue<>();
        this.scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1,new CustomNamedThreadFactory(threadNamePrefix));
        this.fullBackupThreadPoolExecutor = new ScheduledThreadPoolExecutor(1,new CustomNamedThreadFactory(fullBackupThreadNamePrefix));
    }

    private void start(){

        this.initFile();

        IncreaseBackupThread increaseBackupThread = new IncreaseBackupThread();
        FullBackupThread fullBackupThread = new FullBackupThread();
        this.increaseFuture = this.scheduledThreadPoolExecutor.schedule(increaseBackupThread, increaseDelay, TimeUnit.SECONDS);
        this.fullFuture = this.fullBackupThreadPoolExecutor.schedule(fullBackupThread,fullDelay,TimeUnit.SECONDS);
    }

    private void initFile(){
        currentIncreaseFile = new File(parentDir,increaseBackupFileName);
        if (!currentIncreaseFile.exists()){
            try {
                currentIncreaseFile.createNewFile();
            } catch (IOException e) {
                throw new IllegalArgumentException("can not create file");
            }
        }

        try {
            currentIncreaseFileOutputStream = new FileOutputStream(currentIncreaseFile);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("file not found");
        }
    }

    private void stop(){

    }

    public void backup(Snapshot snapshot){
        try {
            this.snapshotQueue.put(snapshot);
        } catch (InterruptedException interruptedException) {
            Thread.currentThread().interrupt();
        }
    }

    public void revert(){

    }

    private class FullBackupThread implements Runnable{

        @Override
        public void run() {
            long current = System.currentTimeMillis();

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(currentIncreaseFile))){
                StringBuilder stringBuilder = new StringBuilder();
                Snapshot snapshot = null;
                String line;

                while ((line = bufferedReader.readLine()) != null){
                    String[] components = line.split("\t");
                    snapshot = new Snapshot(components);
                    if (snapshot.getCreateTimeStamp() <= current){
                        snapshot.deal(stringBuilder);
                    }
                }

                if (snapshot != null){
                    String str = snapshot.getCreateTimeStamp() + "\t" + stringBuilder;
                    FileWriter fileWriter = new FileWriter(currentFullFile);
                    fileWriter.write(str);
                    fileWriter.write(System.lineSeparator());
                    fileWriter.flush();
                    fileWriter.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class IncreaseBackupThread implements Runnable{

        @Override
        public void run() {
            long current = System.currentTimeMillis();
            Snapshot record = SnapshotBackUpDataSource.this.snapshotQueue.peek();
            try(FileWriter fileWriter = new FileWriter(currentIncreaseFile)) {
                if (record != null && record.getCreateTimeStamp() <= current){
                    record = SnapshotBackUpDataSource.this.snapshotQueue.poll();
                    while (record != null && record.getCreateTimeStamp() <= current){
                        fileWriter.write(record.toOperatorString());
                        fileWriter.write(System.lineSeparator());
                        record = SnapshotBackUpDataSource.this.snapshotQueue.poll();
                    }
                    fileWriter.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
