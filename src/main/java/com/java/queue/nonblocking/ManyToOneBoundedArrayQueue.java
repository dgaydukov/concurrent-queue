package com.java.queue.nonblocking;

import com.java.queue.interfaces.Queue;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

/**
 * Here we assume that we would have multiple producers and single consumer
 * The code for poll is same as for OneToOneBoundedArrayQueue, but the code for offer is a bit complex
 * Now we use CAS operator to atomically update values
 */
public class ManyToOneBoundedArrayQueue<T> implements Queue<T> {
    private volatile long head;
    private volatile long tail;
    private final int capacity;
    private final Object[] buffer;

    private static final VarHandle HEAD;
    private static final VarHandle TAIL;
    private static final VarHandle BUFFER;
    static {
        try {
            MethodHandles.Lookup l = MethodHandles.lookup();
            HEAD = l.findVarHandle(OneToOneBoundedArrayQueue.class, "head", long.class);
            TAIL = l.findVarHandle(OneToOneBoundedArrayQueue.class, "tail",  long.class);
            BUFFER = MethodHandles.arrayElementVarHandle(Object[].class);
        } catch (ReflectiveOperationException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public ManyToOneBoundedArrayQueue(int capacity){
        this.capacity = capacity;
        buffer = new Object[capacity];

    }

    @Override
    public boolean offer(T t) {
        do {
            if (tail - head == capacity) {
                return false;
            }
        } while (!TAIL.compareAndSet(this, tail, tail + 1));

        int index = (int) (tail % capacity);
        BUFFER.setRelease(buffer, index, t);
        return true;
    }

    @Override
    public T poll() {
        if (tail == head){
            return null;
        }
        int index = (int) (head % capacity);
        T t = (T) BUFFER.get(buffer, index);
        // only update head, if we got non-null, this would ensure that array item became visible to the thread
        if (t != null){
            BUFFER.setRelease(buffer, index, null);
            HEAD.setRelease(this, head+1);
        }
        return t;
    }

    @Override
    public int getSize() {
        return (int) (tail - head);
    }
}