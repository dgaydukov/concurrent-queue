package com.java.queue.list;

public class LinkedList<T> implements List<T> {
    static class Holder<T>{
        private final T value;
        public Holder(T t){
            value = t;
        }
        public T getValue(){
            return value;
        }
        public Holder<T> next;
    }

    @Override
    public void add(T t) {

    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
