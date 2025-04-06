package com.java.queue.blocking;

import com.java.queue.interfaces.BlockingQueue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BlockingQueueTest {
    private BlockingQueue<String> queue;

    @BeforeEach
    public void beforeEach() {
        queue = new BoundedArrayBlockingQueue<>(10);
    }

    @Test
    public void testPutAndTake() {
        Assertions.assertEquals(0, queue.getSize(), "size should be 0");
        for (int i = 0; i < 10; i++) {
            queue.put("msg_" + i);
        }
        Assertions.assertEquals(10, queue.getSize(), "size should be 10");
        for (int i = 0; i < 10; i++) {
            Assertions.assertEquals("msg_" + i, queue.take(), "take mismatch");
        }
        Assertions.assertEquals(0, queue.getSize(), "size should be 0");
    }

    @Test
    public void testMultiThreaded() throws InterruptedException {
        Runnable takeAndPut = () -> {
            System.out.println("Start takeAndPut");
            try {
                long timestamp = System.currentTimeMillis();
                Assertions.assertEquals("msg_1", queue.take(), "takeAndPut mismatch");
                long timePassed = System.currentTimeMillis() - timestamp;
                Assertions.assertTrue(timePassed > 1_000, "timePassed should be more then 1 sec: timePassed=" + timePassed);
                Thread.sleep(2_000);
                queue.put("msg_2");
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("End takeAndPut");
        };
        Runnable putAndTake = () -> {
            System.out.println("Start putAndTake");
            try {
                Thread.sleep(1_000);
                queue.put("msg_1");
                Thread.sleep(1_000);
                long timestamp = System.currentTimeMillis();
                Assertions.assertEquals("msg_2", queue.take(), "putAndTake mismatch");
                long timePassed = System.currentTimeMillis() - timestamp;
                Assertions.assertTrue(timePassed > 1_000, "timePassed should be more then 1 sec: timePassed=" + timePassed);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("End putAndTake");
        };
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.execute(takeAndPut);
        service.execute(putAndTake);

        service.shutdown();
        service.awaitTermination(10, TimeUnit.SECONDS);
    }
}