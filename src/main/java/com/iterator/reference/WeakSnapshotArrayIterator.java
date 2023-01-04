package com.iterator.reference;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2023-01-04 16:27
 **/
public class WeakSnapshotArrayIterator<E> implements Iterator<E> {

    private long createTimestamp;
    private final List<E> list;

    private int cnt;
    private int idx;
    private long [] addTimestamp;
    private long [] delTimestamp;

    public WeakSnapshotArrayIterator(WeakSnapshotArrayList<E> list,long [] addTimestamp,long [] delTimestamp) {
        this.list = list;
        this.addTimestamp = Arrays.copyOf(addTimestamp,addTimestamp.length);
        this.delTimestamp = Arrays.copyOf(delTimestamp,delTimestamp.length);
        this.createTimestamp = System.currentTimeMillis();
        this.idx = 0;
        this.cnt = list.getActualSize();
    }

    public long getCreateTimestamp() {
        return createTimestamp;
    }

    @Override
    public boolean hasNext() {
        return cnt > 0;
    }

    @Override
    public E next() {
        E e = null;

        while (idx < list.size()){
            //这里的前闭后开 非常重要 因为cpu执行速度过快，导致快照的创建时间和元素的添加和删除时间可能在ms上是重合的。
            //如果使用都是开区间，会导致添加进去的元素不可见或者已经删除的元素却可见的问题
            //从这个角度来说 使用时间戳作为快照标记不是很好的选择。
            if (createTimestamp >= addTimestamp[idx] && createTimestamp < delTimestamp[idx]){
                e = list.get(idx++);
                cnt--;
                break;
            }
            idx++;
        }

        return e;
    }
}
