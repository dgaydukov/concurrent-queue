package com.java.queue.list;

/**
 * Basic list interface, designed to be array-based and run all operations in O(1)
 * We don't have remove(T t) - because this operation will have to scan whole array and perform with O(n)
 */
public interface ArrayList<T> {
    int add(T t);
    T get(int index);
    boolean remove(int index);
    int size();
}
