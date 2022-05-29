package com.composite.filesystem;

public abstract class AbstractFileSystemNode {
    protected String path;
    protected Directory parent;

    public abstract int countOfFiles();

    public abstract long sizeOfFiles();

    /**
     * 增量变化监听函数
     * @param changeOfCount 文件数量变化个数
     * @param changeOfSize 文件大小变化个数
     */
    public abstract void recalculate(int changeOfCount,long changeOfSize);

    public String getPath() {
        return path;
    }

    public void setParent(Directory parent) {
        this.parent = parent;
    }
}
