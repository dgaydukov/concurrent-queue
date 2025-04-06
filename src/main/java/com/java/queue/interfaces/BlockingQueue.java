package com.java.queue.interfaces;

/**
 * put/take is the same as offer/poll for standard queue
 * The naming difference here, is that put/take would wait until it can add element into queue or remove and return
 */
public interface BlockingQueue<T> extends Queue<T> {
    void put(T t);
    T take();
}