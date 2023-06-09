package com.example.mockito.lesson03;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

public class DeepMockTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private Lesson03Service lesson03Service;

    @Mock
    private Lesson03 lesson03;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDeepMock() {
        lesson03Service.get().foo();
    }
}
