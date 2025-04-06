package com.java.queue.singlethread;

import com.java.queue.interfaces.Queue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class BoundedArrayQueueTest {

    public static Stream<Queue<String>> getQueues() {
        return Stream.of(new BoundedArrayQueue<>(10), new BoundedArrayQueue2<>(10));
    }

    @ParameterizedTest
    @MethodSource("getQueues")
    public void testAddItems(Queue<String> queue){
        Assertions.assertEquals(0, queue.getSize(), "size should be 0");
        Assertions.assertNull(queue.poll(), "queue should be empty");
        for (int i = 0; i < 10; i++){
            Assertions.assertTrue(queue.offer("msg_"+i), "should add message successfully");
        }
        Assertions.assertEquals(10, queue.getSize(), "size should be 10");
        // check that can't add any more items
        for (int i = 0; i < 10; i++){
            Assertions.assertFalse(queue.offer("msg_"+i), "should not be able to add items");
        }
    }

    @ParameterizedTest
    @MethodSource("getQueues")
    public void testPollItems(Queue<String> queue){
        Assertions.assertNull(queue.poll(), "queue should be empty");
        for (int i = 0; i < 10; i++){
            queue.offer("msg_"+i);
        }
        for (int i = 0; i < 10; i++){
            Assertions.assertEquals("msg_"+i, queue.poll(), "polled item mismatch");
        }
        Assertions.assertNull(queue.poll(), "queue should be empty");
        Assertions.assertEquals(0, queue.getSize(), "size should be 0");
    }

    @ParameterizedTest
    @MethodSource("getQueues")
    public void testAddAndPoll(Queue<String> queue){
        Assertions.assertEquals(0, queue.getSize(), "size should be 0");
        for (int i = 0; i < 10; i++){
            Assertions.assertTrue(queue.offer("msg_"+i), "should add message successfully");
        }
        Assertions.assertEquals(10, queue.getSize(), "size should be 10");
        for (int i = 0; i < 5; i++){
            Assertions.assertEquals("msg_"+i, queue.poll(), "polled item mismatch");
        }
        Assertions.assertEquals(5, queue.getSize(), "size should be 5");
        for (int i = 10; i < 15; i++){
            Assertions.assertTrue(queue.offer("msg_"+i), "should add message successfully");
        }
        Assertions.assertEquals(10, queue.getSize(), "size should be 10");
        for (int i = 5; i < 15; i++){
            Assertions.assertEquals("msg_"+i, queue.poll(), "polled item mismatch");
        }
        Assertions.assertEquals(0, queue.getSize(), "size should be 0");
        Assertions.assertNull(queue.poll(), "queue should be empty");
    }
}
