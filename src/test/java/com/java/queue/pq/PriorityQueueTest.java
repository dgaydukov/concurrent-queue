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
        Assertions.assertEquals(0, queue.getSize(), "size should be 0");
        for (int i = 0; i < 10; i++){
            Assertions.assertTrue(queue.offer(i), "should offer element successfully");
        }
        Assertions.assertEquals(10, queue.getSize(), "size should be 10");
        for (int i = 0; i < 10; i++){
            Assertions.assertEquals(i, queue.poll(), "poll mismatch");
        }
        Assertions.assertEquals(0, queue.getSize(), "size should be 0");
    }

    @Test
    public void priorityTest(){
        Assertions.assertEquals(0, queue.getSize(), "size should be 0");
        int n = 100;
        for (int i = n; i > 0; i--){
            Assertions.assertTrue(queue.offer(i), "should offer element successfully");
        }
        Assertions.assertEquals(n, queue.getSize(), "size should be "+n);
        for (int i = 1; i <= n; i++){
            Assertions.assertEquals(i, queue.poll(), "poll mismatch");
        }
        Assertions.assertEquals(0, queue.getSize(), "size should be 0");
    }

    @Test
    public void priorityOfferAndPollTest(){
        Assertions.assertEquals(0, queue.getSize(), "size should be 0");
        // add 5 elements
        for (int i = 5; i > 0; i--){
            Assertions.assertTrue(queue.offer(i), "should offer element successfully");
        }
        Assertions.assertEquals(5, queue.getSize(), "size should be 5");
        // read 3 elements
        for (int i = 1; i <= 3; i++){
            Assertions.assertEquals(i, queue.poll(), "poll mismatch");
        }
        Assertions.assertEquals(2, queue.getSize(), "size should be 2");
        for (int i = 10; i > 5; i--){
            Assertions.assertTrue(queue.offer(i), "should offer element successfully");
        }
        Assertions.assertEquals(7, queue.getSize(), "size should be 7");
        for (int i = 4; i <= 6; i++){
            Assertions.assertEquals(i, queue.poll(), "poll mismatch");
        }
        Assertions.assertEquals(4, queue.getSize(), "size should be 4");
    }
}
