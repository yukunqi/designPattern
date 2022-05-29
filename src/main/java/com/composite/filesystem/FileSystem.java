package com.composite.filesystem;

import java.nio.file.FileAlreadyExistsException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FileSystem extends Directory{

    private static final String SYSTEM_ROOT_PATH = "/";

    private static final Map<String,Directory> directoryMap = new ConcurrentHashMap<>();

    public FileSystem() throws FileAlreadyExistsException {
        super(SYSTEM_ROOT_PATH);
        FileSystem.registerDirectory(this);
    }

    public static Directory getDirectory(String dir){
        return directoryMap.get(dir);
    }

    public static void registerDirectory(Directory dir){
        directoryMap.putIfAbsent(dir.getPath(),dir);
    }

    public static void clearFs(){
        directoryMap.clear();
    }
}
