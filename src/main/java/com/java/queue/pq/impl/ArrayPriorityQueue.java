package com.java.queue.pq.impl;

import com.java.queue.pq.PriorityQueue;

import java.util.Arrays;

public class ArrayPriorityQueue<T extends Comparable<T>> implements PriorityQueue<T> {
    private final int growSize;
    private Object[] buffer;
    private int capacity;
    private int head;
    private int tail;
    private int size;

    public ArrayPriorityQueue(int initialCapacity){
        this(initialCapacity, initialCapacity/2);
    }
    public ArrayPriorityQueue(int initialCapacity, int growSize){
        this.growSize = growSize;
        capacity = initialCapacity;
        buffer = new Object[capacity];
    }

    private void grow(){
        capacity = capacity + growSize;
        buffer = Arrays.copyOf(buffer, capacity);
    }

    @Override
    public boolean offer(T t) {
        if (size == capacity){
            grow();
            tail = size;
        }
        if (size == 0){
            buffer[tail] = t;
        } else {
            // go through array and compare with each element
            boolean inserted = false;
            for (int i = head; i < tail; i++){
                int compare = t.compareTo((T)buffer[i]);
                if (compare < 1){
                    inserted = true;
                    // move right on one position all elements
                    for (int j = tail; j > i; j--){
                        buffer[j] = buffer[j-1];
                    }
                    buffer[i] = t;
                    break;
                }
            }
            // if we loop over, but didn't find less or equal, we just add element on top
            if (!inserted){
                buffer[tail] = t;
            }
        }
        tail++;

        if (tail == capacity){
            tail = 0;
        }
        size++;
        return true;
    }

    @Override
    public T poll() {
        if (size > 0){
            size--;
            T t = (T) (buffer[head++]);
            if (head == capacity){
                head = 0;
            }
            return t;
        }
        return null;
    }

    @Override
    public int getSize() {
        return size;
    }
}
