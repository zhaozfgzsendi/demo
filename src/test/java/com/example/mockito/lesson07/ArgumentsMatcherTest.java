package com.example.mockito.lesson07;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ArgumentsMatcherTest {

    @Test
    public void basicTest() {
        List<String> list = mock(ArrayList.class);

        when(list.get(eq(0))).thenReturn("first");
        assertThat(list.get(0), equalTo("first"));
        assertThat(list.get(1), nullValue());

    }

    @Test
    public void testComplex() {
        Foo foo = mock(Foo.class);
        when(foo.function(isA(Parent.class))).thenReturn(100);
        int function = foo.function(new Child1());
        assertThat(function, equalTo(100));
    }

    static class Foo{
        int function(Parent p) {
            return p.work();
        }
    }
    interface Parent {
        int work();
    }

    class Child implements Parent {
        @Override
        public int work() {
            throw  new RuntimeException();
        }
    }
    class Child1 implements Parent {
        @Override
        public int work() {
            throw  new RuntimeException();
        }
    }

}
