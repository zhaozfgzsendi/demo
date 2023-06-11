package com.example.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class CalculatorTest {

    @Parameterized.Parameters
    public static Collection<?> data() {
        Object[][] objects = {
                {0, 0},
                {1, 1},
                {-1, 1}
        };
        return Arrays.stream(objects).collect(Collectors.toList());
    }
    int input;
    int result;

    public CalculatorTest(int input, int result) {
        this.input = input;
        this.result = result;
    }

    @Test
    public void testAbs() {
        int r = Math.abs(this.input);
        assertEquals(this.result, r);
    }

    @Test
    public void evaluatesExpression() {
        Calculator calculator = new Calculator();
        int sum = calculator.evaluate("1+2+3");
        assertEquals(6, sum);
    }

    @Test(expected = NumberFormatException.class)
    public void testException() {
        new Calculator().evaluate("");
    }

}