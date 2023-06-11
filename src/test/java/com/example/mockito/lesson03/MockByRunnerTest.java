package com.example.mockito.lesson03;

import com.example.mockito.quickstart.Account;
import com.example.mockito.quickstart.AccountDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class MockByRunnerTest {

    @Test
    public void testMock() {
        AccountDAO accountDAO = mock(AccountDAO.class, Mockito.RETURNS_SMART_NULLS);
        Account account = accountDAO.findAccount("x", "y");
        System.out.println(account);
    }
}
