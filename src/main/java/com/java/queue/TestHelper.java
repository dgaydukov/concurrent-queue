package com.java.queue;

import java.util.concurrent.CountDownLatch;

public class TestHelper {
    public static void sleep(int ms){
        try{
            Thread.sleep(ms);
        } catch (InterruptedException ex){
            throw new RuntimeException(ex);
        }
    }

    public static void waitLatch(CountDownLatch latch){
        try{
            latch.await();
        } catch (InterruptedException ex){
            throw new RuntimeException(ex);
        }
    }
}
