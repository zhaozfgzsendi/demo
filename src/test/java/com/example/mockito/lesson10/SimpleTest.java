package com.example.mockito.lesson10;

import org.junit.Test;

import static com.example.mockito.lesson10.GreaterThan.gt;
import static org.junit.Assert.assertThat;

public class SimpleTest {

    @Test
    public void test() {
//        assertThat(1, lt(3));
        assertThat(10, gt(5));
        assertThat(10, new GreaterThan<Integer>(4));
    }
}
