package com.java.queue.interfaces;

public interface Queue<T>{
    boolean offer(T t);
    T poll();
    int getSize();
}