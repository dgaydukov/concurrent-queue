package com.java.concurrentqueue.queue;

public class SimpleQueueImpl<T> implements SimpleQueue<T>{
    private Object[] buffer;
    private int head;
    private int tail;
    private int size;
    private int capacity;

    public SimpleQueueImpl(int capacity){
        this.capacity = capacity;
        buffer = new Object[capacity];
    }

    @Override
    public boolean offer(T t) {
        if (size == capacity){
            return false;
        }
        size++;
        buffer[tail++] = t;
        if (tail == capacity - 1){
            tail = 0;
        }
        return true;
    }

    @Override
    public T poll() {
        if (size == 0){
            return null;
        }
        size--;
        T element = (T) buffer[head++];
        if (head == capacity - 1){
            head = 0;
        }
        return element;
    }

    @Override
    public int getSize() {
        return size;
    }
}