package com.example.mockito.lesson09;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class AssertMatcherTest {

    @Test
    public void test() {
        int i = 10;
        assertThat(i, equalTo(10));
        assertEquals(i, 10);
    }

    @Test
    public void test2() {
        assertThat(10, either(equalTo(10)).or(equalTo(20)));
        assertEquals(10, both(equalTo(10)).and(not(equalTo(20))));
    }
}
