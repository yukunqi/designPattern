package com.composite.filesystem;

import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Directory extends AbstractFileSystemNode{

    private List<AbstractFileSystemNode> subNodeList;

    private AtomicInteger countOfFiles;

    private AtomicLong sizeOfFiles;

    public Directory(String path) throws FileAlreadyExistsException {

        String dir = path.substring(0,path.lastIndexOf('/'));

        this.path = path;
        this.initDirectory();


        FileSystem.registerDirectory(this);
    }

    private void initDirectory(){
        this.subNodeList = new ArrayList<>();
        this.countOfFiles = new AtomicInteger();
        this.sizeOfFiles = new AtomicLong();
    }

    public int getCountOfFiles() {
        return countOfFiles.get();
    }

    public long getSizeOfFiles() {
        return sizeOfFiles.get();
    }

    @Override
    public void recalculate(int changeOfCount, long changeOfSize) {
        this.countOfFiles.addAndGet(changeOfCount);
        this.sizeOfFiles.addAndGet(changeOfSize);

        if (parent != null){
            this.parent.recalculate(changeOfCount,changeOfSize);
        }
    }

    @Override
    public int countOfFiles() {

        int count = 0;

        for (int i = 0; i < subNodeList.size(); i++) {
            AbstractFileSystemNode abstractFileSystemNode = subNodeList.get(i);
            count+=abstractFileSystemNode.countOfFiles();
        }

        this.countOfFiles.set(count);

        return count;
    }

    @Override
    public long sizeOfFiles() {
        long size = 0L;

        for (int i = 0; i < subNodeList.size(); i++) {
            AbstractFileSystemNode abstractFileSystemNode = subNodeList.get(i);
            size+=abstractFileSystemNode.sizeOfFiles();
        }

        this.sizeOfFiles.set(size);

        return size;
    }

    public void addSubNode(AbstractFileSystemNode node){
        subNodeList.add(node);
        node.setParent(this);

        this.recalculate(node.countOfFiles(),node.sizeOfFiles());
    }

    public void removeSubNode(String path){
        int deleteIndex = -1;

        for (int i = 0; i < subNodeList.size(); i++) {
            AbstractFileSystemNode abstractFileSystemNode = subNodeList.get(i);
            if (abstractFileSystemNode.getPath().equals(path)){
                deleteIndex = i;
                break;
            }
        }

        if (deleteIndex != -1){
            AbstractFileSystemNode removeNode = this.subNodeList.remove(deleteIndex);
            removeNode.parent = null;

            this.recalculate(-removeNode.countOfFiles(),-removeNode.sizeOfFiles());
        }
    }

    public File getFile(String fileName){
        for (AbstractFileSystemNode node : subNodeList){
            if (node instanceof File){
                if(((File) node).getFileName().equals(fileName)){
                    return (File) node;
                }
            }
        }

        return null;
    }
}
