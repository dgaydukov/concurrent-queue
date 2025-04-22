package com.java.queue.list;

public class LinkedListPerson implements LinkedList<Person> {
    private int size;

    private Person first;
    private Person current;
    private Person read;


    /**
     * Adding takes O(n)
     */
    @Override
    public void add(Person person) {
        if (size == 0){
            first = person;
        }
        size++;
        if (current == null){
            current = person;
        } else {
            Person prev = current.prev;
            if (prev == null){
                current.prev = current;
                current.prev.next = person;
            } else {
                prev.next = current;
            }
            current = person;
        }
    }

    public void addToFirst(Person person) {
        size++;
        if (first == null){
            first = person;
        } else if (first.next == null){
            first.next = person;
        } else {
            Person p = first.next;
            while (p.next != null){
                p = p.next;
            }
            p.next = person;
        }
    }

    @Override
    public Person next() {
        if (read == null){
            read = first;
        }
        Person p = read;
        read = read.next;
        return p;
    }

    @Override
    public void remove() {
        // we should remove current and reset link
        read.prev = read.next;
        size--;
    }

    @Override
    public int size() {
        return size;
    }
}
