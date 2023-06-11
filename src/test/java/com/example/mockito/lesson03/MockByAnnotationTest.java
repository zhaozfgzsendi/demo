package com.example.mockito.lesson03;

import com.example.mockito.quickstart.Account;
import com.example.mockito.quickstart.AccountDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MockByAnnotationTest {

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        System.out.println("init()");
    }
    @Mock(answer = Answers.RETURNS_SMART_NULLS)
    private AccountDAO accountDAO;

    @Test
    public void testMock() {
        Account account = accountDAO.findAccount("x", "y");
        System.out.println(account);
    }
}
