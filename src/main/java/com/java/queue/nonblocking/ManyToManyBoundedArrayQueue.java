package com.java.queue.nonblocking;

import com.java.queue.interfaces.Queue;

public class ManyToManyBoundedArrayQueue<T> implements Queue<T> {

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