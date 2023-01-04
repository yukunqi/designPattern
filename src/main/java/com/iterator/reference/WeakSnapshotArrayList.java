package com.iterator.reference;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2023-01-04 16:28
 **/
public class WeakSnapshotArrayList<E> extends ArrayList<E> {

    private long [] addTimestamp;
    private long [] delTimestamp;

    private List<WeakReference<WeakSnapshotArrayIterator<E>>> snapshotIteratorList = new ArrayList<>();

    /**
     不包括标记删除的元素个数
     *
     */
    private int actualSize = 0;

    public WeakSnapshotArrayList(int initialCapacity) {
        super(initialCapacity);
        addTimestamp = new long[initialCapacity];
        delTimestamp = new long[initialCapacity];
    }

    public WeakSnapshotArrayList() {
        this.addTimestamp = new long[0];
        this.delTimestamp = new long[0];
    }

    public WeakSnapshotArrayList(Collection<? extends E> c) {
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

        boolean canDelete = true;
        for (WeakReference<WeakSnapshotArrayIterator<E>> weakReference : this.snapshotIteratorList){
            WeakSnapshotArrayIterator<E> weakSnapshotArrayIterator = weakReference.get();
            if (weakSnapshotArrayIterator != null && weakSnapshotArrayIterator.getCreateTimestamp() <= delTimestamp[i]){
                canDelete = false;
                break;
            }
        }

        if (canDelete){
            return super.remove(o);
        }

        return true;
    }

    public int getActualSize() {
        return actualSize;
    }

    public void printf(){
        for (int i = 0; i < size(); i++) {
            System.out.print("[");
            System.out.printf("%s",get(i).toString());
            System.out.print("]\n");
        }
    }


    @Override
    public Iterator<E> iterator() {
        WeakSnapshotArrayIterator<E> iterator = new WeakSnapshotArrayIterator<>(this, addTimestamp, delTimestamp);
        snapshotIteratorList.add(new WeakReference<>(iterator));
        return iterator;
    }
}
