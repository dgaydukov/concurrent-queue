package com.java.queue.list;

/**
 * Basic List interface designed to be used with linked list as underlying data structures
 * All operations are supposed to run with O(1) complexity
 * We don't use get(int index), remove(int index) - cause such operation would require traversal of whole list and perform with O(n)
 */
public interface LinkedList<T> {
    void add(T t);
    T next();
    void remove();
    int size();
    void resetIterator();
}