package com.java.queue.pq.impl;

import com.java.queue.pq.IndexedPriorityQueue;

public class IPQImpl<K, V> implements IndexedPriorityQueue<K, V> {
    @Override
    public boolean offer(K key, V value) {
        return false;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public V poll() {
        return null;
    }

    @Override
    public int getSize() {
        return 0;
    }
}
