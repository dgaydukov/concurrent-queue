package com.java.queue.pq;

public interface IndexedPriorityQueue<K, V>{
    boolean offer(K key, V value);
    V get(K key);
    V poll();
    int getSize();
}