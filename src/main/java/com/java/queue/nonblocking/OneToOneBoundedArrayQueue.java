package com.java.queue.nonblocking;

import com.java.queue.interfaces.Queue;

public class OneToOneBoundedArrayQueue implements Queue {
    @Override
    public boolean offer(Object o) {
        return false;
    }

    @Override
    public Object poll() {
        return null;
    }

    @Override
    public int getSize() {
        return 0;
    }
}
