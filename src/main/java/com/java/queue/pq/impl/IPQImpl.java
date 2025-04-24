package com.java.queue.pq.impl;

import com.java.queue.pq.IndexedPriorityQueue;

import java.util.Arrays;
import java.util.HashMap;

public class IPQImpl<K, V extends Comparable<V>> implements IndexedPriorityQueue<K, V> {
    private final int growSize;
    private Object[] buffer;
    private HashMap<K, V> map;
    private int capacity;
    private int head;
    private int tail;
    private int size;

    public IPQImpl(int initialCapacity){
        this(initialCapacity, initialCapacity/2);
    }

    public IPQImpl(int initialCapacity, int growSize){
        this.growSize = growSize;
        capacity = initialCapacity;
        buffer = new Object[capacity];
        map = new HashMap<>();
    }

    private void grow(){
        capacity = capacity + growSize;
        buffer = Arrays.copyOf(buffer, capacity);
    }

    @Override
    public boolean offer(K key, V value) {
        return false;
    }

    @Override
    public V get(K key) {
        return map.get(key);
    }

    @Override
    public V poll() {
        return null;
    }

    @Override
    public int getSize() {
        return size;
    }
}
