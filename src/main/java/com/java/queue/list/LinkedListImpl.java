package com.java.queue.list;

public class LinkedListImpl implements LinkedList<Person> {

    public static void main(String[] args) {
        LinkedListImpl ll = new LinkedListImpl();
        for (int i = 0; i < 10; i++){
            ll.add(new Person("person_"+i, i*10));
        }
        for (int i = 0; i < 10; i++){
            System.out.println(ll.next());
        }
    }
    private int size;
    private int index;
    // read first
    private Person first;
    // append to last
    private Person last;
    // for iteration for next. reset would put current=first
    private Person current;


    @Override
    public void add(Person person) {
        size++;
    }

    @Override
    public Person next() {
        return null;
    }

    @Override
    public void remove() {
        size--;
    }

    @Override
    public int size() {
        return size;
    }
}
