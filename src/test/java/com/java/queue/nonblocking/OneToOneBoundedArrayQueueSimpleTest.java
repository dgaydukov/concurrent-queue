package com.java.queue.nonblocking;

import com.java.queue.TestHelper;
import com.java.queue.interfaces.Queue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class OneToOneBoundedArrayQueueSimpleTest {

    /**
     * You can check the veracity of this test by substitute queue by single threaded
     * Queue<String> queue = new BoundedArrayQueue<>(10);
     * You will notice that test start to fail
     */
    @Test
    public void testOfferAndPoll1000Times(){
        Queue<String> queue = new OneToOneBoundedArrayQueueSimple<>(10);
        for (int i = 0; i < 1_000; i++){
            testOfferAndPoll(queue);
        }

    }

    private void testOfferAndPoll(Queue<String> queue){
        // adding and reading 100 elements
        CountDownLatch latch = new CountDownLatch(1);
        int n = 100;
        new Thread(()->{
            for (int i = 0; i < n; i++){
                while (!queue.offer("msg_"+i)){}
            }
        }).start();

        new Thread(()->{
            List<String> messages = new ArrayList<>();
            for(int i = 0; i < 10_000_000; i++){
                String msg = queue.poll();
                if (msg != null){
                    messages.add(msg);
                }
            }
            Assertions.assertEquals(100, messages.size(), "should be 100 elements");
            latch.countDown();
        }).start();
        TestHelper.waitLatch(latch);
    }
}
