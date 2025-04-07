package com.java.queue.nonblocking;

import com.java.queue.interfaces.Queue;

/**
 * Here we assume that we would have multiple producers and single consumer
 * The code for poll is same as for OneToOneBoundedArrayQueue, but the code for offer is a bit complex
 * Now we use CAS operator to atomically update values
 */
public class ManyToOneBoundedArrayQueue<T> implements Queue<T> {

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