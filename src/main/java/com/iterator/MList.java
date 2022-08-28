package com.iterator;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2022-06-19 16:37
 **/
public interface MList<E> {
    /**
     *
     * @param element
     */
    void add(E element);
    /**
     *
     * @param element
     */
    void remove(E element);

    /**
     *
     * @return
     */
    MIterator<E> iterator();
}
