package com.backup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2023-01-05 23:00
 **/
public class SnapshotBackUpDataSource {

    private static final Logger log = LoggerFactory.getLogger(SnapshotBackUpDataSource.class);
    private BlockingQueue<Snapshot> snapshotQueue;
    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
    private ScheduledThreadPoolExecutor fullBackupThreadPoolExecutor;
    private final String threadNamePrefix = "increase-backup-threadPool";
    private final String fullBackupThreadNamePrefix = "full-backup-threadPool";
    private int increaseDelay = 1;
    private int fullDelay = 1 * 6;
    private String parentDir;
    private static final String defaultParentDir = "/Users/kunqi.yu/Downloads/snapshot";
    private String increaseBackupFileName = "snapshot-increase";
    private String fullBackupFileName = "snapshot-full";
    private File currentIncreaseFile;
    private File currentFullFile;


    public SnapshotBackUpDataSource() {
        this(defaultParentDir);
    }

    public SnapshotBackUpDataSource(String parentDir) {
        this.parentDir = parentDir;
        this.snapshotQueue = new LinkedBlockingQueue<>();
        String hash = String.valueOf(this.hashCode());
        this.scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, new CustomNamedThreadFactory(hash+"-"+threadNamePrefix));
        this.fullBackupThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, new CustomNamedThreadFactory(hash+"-"+fullBackupThreadNamePrefix));
        this.start();
    }

    private void start() {

        this.initFile();

        IncreaseBackupThread increaseBackupThread = new IncreaseBackupThread();
        FullBackupThread fullBackupThread = new FullBackupThread();
        this.scheduledThreadPoolExecutor.schedule(increaseBackupThread, increaseDelay, TimeUnit.SECONDS);
        this.fullBackupThreadPoolExecutor.schedule(fullBackupThread, fullDelay, TimeUnit.SECONDS);
    }

    private void initFile() {
        File directory = new File(parentDir);
        if (!directory.exists()){
            directory.mkdirs();
        }

        currentIncreaseFile = new File(parentDir, increaseBackupFileName);
        if (!currentIncreaseFile.exists()) {
            try {
                currentIncreaseFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                throw new IllegalArgumentException("can not create increase file");
            }
        }

        currentFullFile = new File(parentDir, fullBackupFileName);
        if (!currentFullFile.exists()) {
            try {
                currentFullFile.createNewFile();
            } catch (IOException e) {
                throw new IllegalArgumentException("can not create full file");
            }
        }

    }

    public void stop() {
        this.scheduledThreadPoolExecutor.shutdown();
        this.fullBackupThreadPoolExecutor.shutdown();
    }

    public void backup(Snapshot snapshot) {
        try {
            this.snapshotQueue.put(snapshot);
        } catch (InterruptedException interruptedException) {
            Thread.currentThread().interrupt();
        }
    }

    public String restore(long ts) {
        StringBuilder stringBuilder = null;
        long increaseStart = 0;
        //find in full backup
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(currentFullFile))) {
            String prev;
            String line = null;
            long contentTs = 0;
            do{
                prev = line;
                line = bufferedReader.readLine();
                if (line == null){
                    break;
                }
                contentTs = Long.parseLong(line.split("\t")[0]);
            }while (contentTs <= ts);

            increaseStart = contentTs;

            if (prev != null){
                stringBuilder = new StringBuilder(prev.split("\t")[1]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        //find in increase backup
        if (stringBuilder == null){
            stringBuilder = new StringBuilder();
        }

        try(BufferedReader bufferedReader =  new BufferedReader(new FileReader(currentIncreaseFile))){

            //increase deal in [increaseStart,ts] area
            String line;
            while((line = bufferedReader.readLine()) != null){
                String[] components = line.split("\t");
                Snapshot snapshot = new Snapshot(components);
                if (snapshot.getCreateTimeStamp() > ts){
                    break;
                }

                if (increaseStart <= snapshot.getCreateTimeStamp()){
                    snapshot.deal(stringBuilder);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

    private class FullBackupThread implements Runnable {

        @Override
        public void run() {
            log.info("full back up start");
            long current = System.currentTimeMillis();
            //fixme 多线程并发会全量备份同样的数据到文件中，需要从当前全量数据文件的末尾时间戳(fullFileEnd)作为起点开始搜索增量文件 将(fullFileEnd,current]范围的数据变化重放然后更新数据文件
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(currentIncreaseFile))) {
                StringBuilder stringBuilder = new StringBuilder();
                Snapshot snapshot = null;
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    String[] components = line.split("\t");
                    snapshot = new Snapshot(components);
                    if (snapshot.getCreateTimeStamp() <= current) {
                        snapshot.deal(stringBuilder);
                    }
                }

                if (snapshot != null) {
                    String str = snapshot.getCreateTimeStamp() + "\t" + stringBuilder;
                    FileWriter fileWriter = new FileWriter(currentFullFile,true);
                    fileWriter.write(str);
                    fileWriter.write(System.lineSeparator());
                    fileWriter.flush();
                    fileWriter.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }


            log.info("full back up end");
        }
    }

    private class IncreaseBackupThread implements Runnable {

        @Override
        public void run() {
            log.info("increase back up start");
            long current = System.currentTimeMillis();
            Snapshot record = SnapshotBackUpDataSource.this.snapshotQueue.peek();
            //fixme 使用buffered 类 避免并发问题
            try (FileWriter fileWriter = new FileWriter(currentIncreaseFile,true)) {
                if (record != null && record.getCreateTimeStamp() <= current) {
                    record = SnapshotBackUpDataSource.this.snapshotQueue.poll();
                    while (record != null && record.getCreateTimeStamp() <= current) {
                        fileWriter.write(record.toOperatorString());
                        fileWriter.write(System.lineSeparator());
                        record = SnapshotBackUpDataSource.this.snapshotQueue.poll();
                    }
                    fileWriter.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }


            log.info("increase back up end");
        }
    }
}
