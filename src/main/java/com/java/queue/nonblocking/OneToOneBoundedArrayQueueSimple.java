package com.java.queue.nonblocking;

import com.java.queue.interfaces.Queue;

public class OneToOneBoundedArrayQueueSimple<T> implements Queue<T> {
    private volatile long head;
    private volatile long tail;
    private final int capacity;
    private final Object[] buffer;

    public OneToOneBoundedArrayQueueSimple(int capacity){
        this.capacity = capacity;
        buffer = new Object[capacity];
    }

    @Override
    public boolean offer(T t) {
        if (tail - head == capacity){
            return false;
        }
        int index = (int) (tail % capacity);
        buffer[index] = t;
        tail++;
        return true;
    }

    @Override
    public T poll() {
        if (tail == head){
            return null;
        }
        int index = (int) (head % capacity);
        T t = (T) buffer[index];
        /**
         * only increase head counter if you get non-null value
         * For safer concerns change value back to null for this counter to avoid possible double-read problems
         */
        if (t != null){
            buffer[index] = null;
            head++;
        }
        return t;
    }

    @Override
    public int getSize() {
        return (int) (tail - head);
    }
}