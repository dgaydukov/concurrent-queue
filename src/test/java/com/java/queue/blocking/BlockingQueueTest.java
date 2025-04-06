package com.java.queue.blocking;

import com.java.queue.interfaces.BlockingQueue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BlockingQueueTest {
    private BlockingQueue<String> queue;

    @BeforeEach
    public void beforeEach(){
        queue = new BoundedArrayBlockingQueue<>(10);
    }

    @Test
    public void testPutAndTake(){
        Assertions.assertEquals(0, queue.getSize(), "size should be 0");
        for (int i = 0; i < 10; i++){
            queue.put("msg_"+i);
        }
        Assertions.assertEquals(10, queue.getSize(), "size should be 10");
        for (int i = 0; i < 10; i++){
            Assertions.assertEquals("msg_"+i, queue.take(), "take mismatch");
        }
        Assertions.assertEquals(0, queue.getSize(), "size should be 0");
    }
}
