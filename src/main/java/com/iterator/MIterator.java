package com.iterator;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2022-06-19 16:38
 **/
public interface MIterator<E> {
    boolean hasNext();

    E next();
}
