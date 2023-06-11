package com.example.mockito.lesson08;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WildcardArgumentMatcherTest {

    @Mock
    private SimpleService simpleService;

    @Test
    public void wildcardMethod1() {

        when(simpleService.method1(anyInt(), anyString(), anyCollection(), isA(Serializable.class)))
                .thenReturn(100);
        int result = simpleService.method1(1, "2", Collections.EMPTY_LIST, "4");
        assertThat(result, equalTo(100));
    }

    @Test
    public void wildcardMethod2() {

        when(simpleService.method1(anyInt(), eq("2"), anyCollection(), isA(Serializable.class)))
                .thenReturn(100);
        int result = simpleService.method1(1, "2", Collections.EMPTY_LIST, "4");
        assertThat(result, equalTo(100));
    }
    @Test
    public void wildcardMethod3() {

        doNothing().when(simpleService).method2(anyInt(), anyString(), anyCollection(), isA(Serializable.class));
        simpleService.method2(1, "2", Collections.EMPTY_LIST, "4");
        verify(simpleService, times(1)).method2(1, "2", Collections.EMPTY_LIST, "4");
    }

    @Before
    public void destroy() {
        reset(simpleService);
    }
}
