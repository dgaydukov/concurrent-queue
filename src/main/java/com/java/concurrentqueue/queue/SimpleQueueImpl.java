package com.java.concurrentqueue.queue;

public class SimpleQueueImpl<T> implements SimpleQueue<T>{
    @Override
    public boolean offer(T t) {
        return false;
    }

    @Override
    public T poll() {
        return null;
    }

    @Override
    public int getSize() {
        return 0;
    }
}