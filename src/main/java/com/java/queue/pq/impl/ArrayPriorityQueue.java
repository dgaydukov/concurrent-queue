package com.java.queue.pq.impl;

import com.java.queue.pq.PriorityQueue;

import java.util.Arrays;

public class ArrayPriorityQueue<T> implements PriorityQueue<T> {
    private final int capacity;
    private final int growSize;
    private Object[] buffer;
    private int head;
    private int tail;
    private int size;

    public ArrayPriorityQueue(int capacity, int growSize){
        this.capacity = capacity;
        this.growSize = growSize;
        buffer = new Object[capacity];
    }

    private void grow(){
        buffer = Arrays.copyOf(buffer, buffer.length + growSize);
    }

    @Override
    public boolean offer(T t) {
        size++;
        return false;
    }

    @Override
    public T poll() {
        if (size > 0){
            
        }
        return null;
    }

    @Override
    public int getSize() {
        return size;
    }
}
