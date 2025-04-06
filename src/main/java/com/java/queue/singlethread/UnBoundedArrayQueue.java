package com.java.queue.singlethread;

import com.java.queue.interfaces.Queue;

import java.util.Arrays;

public class UnBoundedArrayQueue<T> implements Queue<T> {
    private Object[] buffer;
    private int head;
    private int tail;
    private int size;
    private int capacity;
    private final int growSize;

    public UnBoundedArrayQueue(){
        this(1000, 500);
    }

    public UnBoundedArrayQueue(int initialCapacity, int growSize){
        capacity = initialCapacity;
        this.growSize = growSize;
        buffer = new Object[capacity];
    }

    private void grow(){
        int newCapacity = capacity + growSize;
        Object[] newBuffer = Arrays.copyOf(buffer, newCapacity);
        capacity = newCapacity;
        buffer = newBuffer;
    }

    @Override
    public boolean offer(T t) {
        if (size == capacity){
            grow();
        }
        size++;
        buffer[tail++] = t;
        if (tail == capacity){
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
        if (head == capacity){
            head = 0;
        }
        return element;
    }

    @Override
    public int getSize() {
        return size;
    }
}