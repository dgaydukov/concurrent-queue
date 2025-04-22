package com.java.queue.list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LinkedListPersonTest {
    private LinkedList<Person> list;

    @BeforeEach
    public void beforeEach(){
        list = new LinkedListPerson();
    }

    @Test
    public void addTest(){
        // add 10 elements
        Assertions.assertEquals(0, list.size(), "size should be 0");
        for (int i = 0; i < 10; i++){
            list.add(new Person("name_"+i, i*5));
        }
        Assertions.assertEquals(10, list.size(), "size should be 10");
        for (int i = 10; i < 20; i++){
            list.add(new Person("name_"+i, i*5));
        }
        Assertions.assertEquals(20, list.size(), "size should be 20");
        for (int i = 20; i < 30; i++){
            list.add(new Person("name_"+i, i*5));
        }
        Assertions.assertEquals(30, list.size(), "size should be 30");
        for (int i = 0; i < 30; i++){
            Person p = list.next();
            Assertions.assertEquals("name_"+i, p.getName(), "name mismatch");
            Assertions.assertEquals(i*5, p.getAge(), "age mismatch");
        }
        Assertions.assertNull(list.next(), "should be null");
        Assertions.assertEquals(30, list.size(), "size should be 30");
        list.resetIterator();
        for (int i = 0; i < 30; i++){
            Person p = list.next();
            Assertions.assertEquals("name_"+i, p.getName(), "name mismatch");
            Assertions.assertEquals(i*5, p.getAge(), "age mismatch");
        }
        Assertions.assertNull(list.next(), "should be null");
        Assertions.assertEquals(30, list.size(), "size should be 30");
    }

    @Test
    public void remoteTest(){
        Assertions.assertEquals(0, list.size(), "size should be 0");
        for (int i = 0; i < 10; i++){
            list.add(new Person("name_"+i, i*5));
        }
        Assertions.assertEquals(10, list.size(), "size should be 10");
        list.resetIterator();
        for (int i = 0; i < 10; i++){
            list.remove();
        }
        Assertions.assertEquals(0, list.size(), "size should be 0");
    }
}
