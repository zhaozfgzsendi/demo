package com.example.mockito;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

public class TestDemo {

    @Test
    public void test1() {
        List mockedList = mock(List.class);

        mockedList.add("one");
        mockedList.clear();

        verify(mockedList).add("one");
        verify(mockedList).clear();

    }

    @Test
    public void testStub() {
        LinkedList mockedList = mock(LinkedList.class);

        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new RuntimeException());

        System.out.println(mockedList.get(0));
        try {
            System.out.println(mockedList.get(1));
        } catch (Exception e) {
            System.out.println("cache exception");
        }

        System.out.println(mockedList.get(999));

        verify(mockedList).get(999);
    }

    LinkedList mockedList;

    @Before
    public void setUp() {
        mockedList = mock(LinkedList.class);
    }

    @Test
    public void testMatchers() {
        when(mockedList.get(anyInt())).thenReturn("element");

//        when(mockedList.contains(argThat(isValid()))).thenReturn("elements");

        System.out.println(mockedList.get(999));
        verify(mockedList).get(anyInt());
    }


}
