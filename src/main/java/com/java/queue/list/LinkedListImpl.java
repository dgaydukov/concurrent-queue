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
    // read first
    private Person first;
    // append to last
    private Person last;
    // for iteration for next. reset would put current=first
    private Person current;


    /**
     * Adding takes O(n)
     */
    @Override
    public void add(Person person) {
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
        if (current == null){
            current = first;
        }
        Person p = current;
        current = current.next;
        return p;
    }

    @Override
    public void remove() {
        // we should remove current and reset link
        current.prev = current.next;
        size--;
    }

    @Override
    public int size() {
        return size;
    }
}
