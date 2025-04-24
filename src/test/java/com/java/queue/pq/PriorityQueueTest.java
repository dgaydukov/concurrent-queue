package com.java.queue.pq;

import com.java.queue.pq.impl.ArrayPriorityQueue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PriorityQueueTest {
    private PriorityQueue<Integer> queue;

    @BeforeEach
    public void beforeEach(){
        queue = new ArrayPriorityQueue<>(10);
    }

    @Test
    public void unlimitedGrowthTest(){
        Assertions.assertEquals(0, queue.getSize(), "size should be 0");
        for (int i = 0; i < 10; i++){
            Assertions.assertTrue(queue.offer(i), "should offer element successfully");
        }
        Assertions.assertEquals(10, queue.getSize(), "size should be 10");
        for (int i = 0; i < 10; i++){
            Assertions.assertTrue(queue.offer(i), "should offer element successfully");
        }
        Assertions.assertEquals(20, queue.getSize(), "size should be 20");
        for (int i = 0; i < 10; i++){
            Assertions.assertTrue(queue.offer(i), "should offer element successfully");
        }
        Assertions.assertEquals(30, queue.getSize(), "size should be 30");
    }

    @Test
    public void offerAndPollTest(){
        
    }

}
