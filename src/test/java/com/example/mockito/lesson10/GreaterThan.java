package com.example.mockito.lesson10;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.jupiter.api.TestFactory;

public class GreaterThan<T extends Number> extends BaseMatcher<T> {

    private final T value;

    public GreaterThan(T value) {
        this.value = value;
    }


    @Override
    public boolean matches(Object actual) {
        Class<?> clazz = actual.getClass();
        if (clazz == Integer.class) {
            return (Integer) actual > (Integer) value;
        }

        throw new AssertionError("not supproted");
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("compare numbers integer");
    }

    @TestFactory
    public static <T extends Number> GreaterThan<T> gt(T value) {
        return new GreaterThan<>(value);
    }


}
