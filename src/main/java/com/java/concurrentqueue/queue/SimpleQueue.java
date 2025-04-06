package com.java.concurrentqueue.queue;

public interface SimpleQueue<T>{
    boolean offer(T t);
    T poll();
    int getSize();
}