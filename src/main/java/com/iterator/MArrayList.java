package com.iterator;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2022-06-19 16:40
 **/
public class MArrayList<E> implements MList<E>{

    private Object [] data;
    private int modCount = 0;
    private int cursor = 0;
    static final int DEFAULT_SIZE = 1 << 4;
    static final float factor = 0.75f;
    private int size = 0;

    public MArrayList() {
        this.data = new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(E element) {
        if (size >= data.length * factor){
            expand();
        }

        data[cursor++] = element;
        size++;
    }

    private void expand(){

    }

    @Override
    public void remove(E element) {

    }

    @Override
    public MIterator<E> iterator() {
        return null;
    }
}
