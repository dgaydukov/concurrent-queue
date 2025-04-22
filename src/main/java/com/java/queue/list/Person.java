package com.java.queue.list;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    // if we write linked list and don't have this fields in object, that means we should create holder object for each item in the list
    // this is terrible for low-latency and GC, so to write zero-GC we should have this field in the object itself
    public Person next;
    public Person prev;

    @Override
    public String toString(){
        return "Person[name="+name+", age="+age+"]";
    }
}