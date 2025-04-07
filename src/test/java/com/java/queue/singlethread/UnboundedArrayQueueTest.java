package com.java.queue.singlethread;

import com.java.queue.interfaces.Queue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UnboundedArrayQueueTest {
    private Queue<String> queue;

    @BeforeEach
    public void beforeEach(){
        queue = new UnBoundedArrayQueue<>(10, 1);
    }

    @Test
    public void testUnlimitedAdd(){
        Assertions.assertEquals(0, queue.getSize(), "size should be 0");
        int size = 10_000;
        for (int i = 0; i < size; i++){
            Assertions.assertTrue(queue.offer("msg_"+i));
        }
        Assertions.assertEquals(size, queue.getSize(), "size should be "+size);
    }

    @Test
    public void testPoll(){
        int n = 100;
        Assertions.assertEquals(0, queue.getSize(), "size should be 0");
        Assertions.assertNull(queue.poll(), "queue should be empty");
        for (int i = 0; i < n; i++){
            Assertions.assertTrue(queue.offer("msg_"+i));
        }
        Assertions.assertEquals(n, queue.getSize(), "size should be "+n);
        for (int i = 0; i < n; i++){
            Assertions.assertEquals("msg_"+i, queue.poll(), "poll mismatch");
        }
        Assertions.assertEquals(0, queue.getSize(), "size should be 0");
        Assertions.assertNull(queue.poll(), "queue should be empty");
    }
}
