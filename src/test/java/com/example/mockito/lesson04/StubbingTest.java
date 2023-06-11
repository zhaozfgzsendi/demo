package com.example.mockito.lesson04;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StubbingTest {

    private List<String> list;

    @Before
    public void init() {
        list = mock(List.class);
    }

    @Test
    public void howToUseStubbing() {
        when(list.get(0)).thenReturn("first");
        when(list.get(1)).thenReturn("second");
        when(list.get(2)).thenReturn("third");

        assertThat(list.get(0), equalTo("first"));

        when(list.get(anyInt())).thenThrow(new RuntimeException());
        try {
            list.get(0);
            fail();
        } catch (Exception e) {
            assertThat(e, instanceOf(RuntimeException.class));
        }
    }

    @Test
    public void howToStubbingVoidMethod() {
        doNothing().when(list).clear();
        list.clear();

        verify(list, times(1)).clear();

        doThrow(RuntimeException.class).when(list).clear();

        try {
            list.clear();
            fail();
        } catch (Exception e) {
            assertThat(e, instanceOf(RuntimeException.class));
        }
    }

    @Test
    public void stubbingDoReturn() {
        when(list.get(0)).thenReturn("first");

        doReturn("second").when(list).get(1);

        assertThat(list.get(0), equalTo("first"));
        assertThat(list.get(1), equalTo("second"));

    }

    @Test
    public void iterateSubbing() {
        when(list.size()).thenReturn(1);
        when(list.size()).thenReturn(2);
        when(list.size()).thenReturn(3);

        assertThat(list.size(), equalTo(1));

        when(list.size()).thenReturn(1, 2, 3);
        assertThat(list.size(), equalTo(1));
        assertThat(list.size(), equalTo(2));
        assertThat(list.size(), equalTo(3));
        assertThat(list.size(), equalTo(3));
    }

    @Test
    public void stubbingWhthAnswer() {
        when(list.get(anyInt())).thenAnswer(invocationOnMock -> {
            Integer index = invocationOnMock.getArgument(0, Integer.class);
            return String.valueOf(index * 10);
        });

        assertThat(list.get(0), equalTo("0"));
    }

    @Test
    public void stubbingWithRealCall() {
        StubbingService service = mock(StubbingService.class);
        System.out.println(service.getClass());
        service.getS();
        System.out.println(service.get());

        when(service.getS()).thenReturn("Alex");
        assertThat(service.getS(), equalTo("Alex"));

        when(service.get()).thenCallRealMethod();
        assertThat(service.get(), equalTo(10));
    }


    @After
    public void destroy() {
        reset(this.list);
    }
}
