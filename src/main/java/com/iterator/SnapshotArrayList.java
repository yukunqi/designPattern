package com.iterator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2023-01-04 12:06
 **/
public class SnapshotArrayList<E>  extends ArrayList<E> {

    private long [] addTimestamp;
    private long [] delTimestamp;
    /**
     不包括标记删除的元素个数
     *
     */
    private int actualSize = 0;

    public SnapshotArrayList(int initialCapacity) {
        super(initialCapacity);
        addTimestamp = new long[initialCapacity];
        delTimestamp = new long[initialCapacity];
    }

    public SnapshotArrayList() {
        this.addTimestamp = new long[0];
        this.delTimestamp = new long[0];
    }

    public SnapshotArrayList(Collection<? extends E> c) {
        super(c);
        addTimestamp = new long[c.size()];
        delTimestamp = new long[c.size()];
    }

    @Override
    public boolean add(E e) {
        int size = size();
        addTimestamp[size] = System.currentTimeMillis();
        delTimestamp[size] = Long.MAX_VALUE;
        actualSize++;
        return super.add(e);
    }

    @Override
    public boolean remove(Object o) {
        int i = indexOf(o);
        delTimestamp[i] = System.currentTimeMillis();
        actualSize--;
        return true;
    }

    public int getActualSize() {
        return actualSize;
    }

    @Override
    public Iterator<E> iterator() {
        return new SnapshotArrayIterator<>(this,addTimestamp,delTimestamp);
    }

}
