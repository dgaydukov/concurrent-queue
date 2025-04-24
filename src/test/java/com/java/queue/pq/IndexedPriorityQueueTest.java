package com.java.queue.pq;

import com.java.queue.pq.impl.IPQImpl;
import org.junit.jupiter.api.BeforeEach;

public class IndexedPriorityQueueTest {
    private IndexedPriorityQueue<String, Integer> queue;

    @BeforeEach
    public void beforeEach(){
        queue = new IPQImpl<>(10);
    }
}
