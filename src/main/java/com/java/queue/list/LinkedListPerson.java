package com.java.queue.list;

public class LinkedListPerson implements LinkedList<Person> {
    private int size;

    private Person first;
    private Person last;
    private Person current;


    /**
     * Adding takes O(n)
     */
    @Override
    public void add(Person person) {
        if (size == 0){
            first = person;
            current = first;
        }
        size++;
        if (last != null) {
            Person prev = last.prev;
            if (prev == null) {
                last.prev = last;
                last.prev.next = person;
            } else {
                prev.next = last;
            }
        }
        last = person;
    }

    @Override
    public Person next() {
        if (current == null){
            return null;
        }
        Person p = current;
        current = current.next;
        return p;
    }

    @Override
    public void remove() {
        if (size > 0){
            current.prev = current.next;
            size--;
            if (size == 0){
                first = null;
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void resetIterator() {
        current = first;
    }
}
