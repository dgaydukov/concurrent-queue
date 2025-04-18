package com.java.queue.list;

public class LinkedList2  {

    public static void main(String[] args) {
        LinkedList2 ll = new LinkedList2();
        for (int i = 0; i < 10; i++){
            ll.add(new Person("person_"+i));
        }
        for (int i = 0; i < 10; i++){
            System.out.println(((Person)ll.get(i)).getName());
        }
    }
    private int size;
    private int index;
    private Person first;
    private Person current;


    public static class Person{
        final String name;
        public Person(String name){
            this.name = name;
        }
        public String getName(){
            return name;
        }
        public Person next;
    }


    public void add(Person person) {
        size++;
        if (this.first == null){
            this.first = person;
        } else if (this.first.next == null){
            this.first.next = person;
        }  else {
            Person curr = this.first.next;
            while (curr.next != null){
                curr = curr.next;
            }
            curr.next = person;
        }
    }

    public Object get(int index) {
        int i = 0;
        Person p = first;
        while (i++ < index){
            p = p.next;
        }
        return p;
    }

    public int size() {
        return size;
    }
}
