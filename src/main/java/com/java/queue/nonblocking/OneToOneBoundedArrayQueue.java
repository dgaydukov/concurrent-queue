package com.java.queue.nonblocking;

import com.java.queue.interfaces.Queue;

public class OneToOneBoundedArrayQueue<T> implements Queue<T> {
    private volatile long head;
    private volatile long tail;
    private final int capacity;
    private final Object[] buffer;

    public OneToOneBoundedArrayQueue(int capacity){
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
        if (t != null){
            head++;
        }
        return t;
    }

    @Override
    public int getSize() {
        return (int) (tail - head);
    }
}