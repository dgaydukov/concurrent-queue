package com.java.queue.blocking;

import com.java.queue.interfaces.BlockingQueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedArrayBlockingQueue<T> implements BlockingQueue<T> {
    private final ReentrantLock lock;
    private final Condition full;
    private final Condition empty;
    private final int capacity;
    private final Object[] buffer;

    private int head;
    private int tail;
    private int size;

    public BoundedArrayBlockingQueue(int capacity){
        this.capacity = capacity;
        lock = new ReentrantLock();
        full = lock.newCondition();
        empty = lock.newCondition();
        buffer = new Object[capacity];
    }

    @Override
    public void put(T t) {
        try{
            lock.lock();
            if (size == capacity){
                // queue is full, wait
                full.wait();
            }
            size++;
            empty.signal();
            buffer[tail++] = t;
            if (tail == capacity){
                tail = 0;
            }
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public T take() {
        try{
            lock.lock();
            if (size == 0){
                // queue is empty, wait
                empty.wait();
            }
            size--;
            full.signal();
            T t = (T) buffer[head++];
            if (head == capacity){
                head = 0;
            }
            return t;
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int getSize() {
        try{
            lock.lock();
            return size;
        } finally {
            lock.unlock();
        }
    }


    @Override
    public boolean offer(T t) {
        throw new RuntimeException("method not supported");
    }

    @Override
    public T poll() {
        throw new RuntimeException("method not supported");
    }
}
