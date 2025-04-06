package com.java.queue.singlethread;

import com.java.queue.interfaces.Queue;

public class BoundedArrayQueue2<T> implements Queue<T> {
    private Object[] buffer;
    private long head;
    private long tail;
    private int capacity;

    public BoundedArrayQueue2(){
        this(1000);
    }

    public BoundedArrayQueue2(int capacity){
        this.capacity = capacity;
        buffer = new Object[capacity];
    }

    @Override
    public boolean offer(T t) {
        if (tail - head == capacity - 1){
            return false;
        }
        int index = (int) (tail++ % capacity);
        buffer[index] = t;
        return true;
    }

    @Override
    public T poll() {
        if (tail == head){
            return null;
        }
        int index = (int) (head++ % capacity);
        T element = (T) buffer[index];
        return element;
    }

    @Override
    public int getSize() {
        return (int) (tail - head);
    }
}