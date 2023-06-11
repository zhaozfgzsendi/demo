package com.example.mockito.lesson03;

import com.example.mockito.quickstart.Account;
import com.example.mockito.quickstart.AccountDAO;
import lombok.val;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.mock;

public class MockByRuleTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock(answer = Answers.RETURNS_SMART_NULLS)
    private AccountDAO accountDAO;

    @Test
    public void test() {
        AccountDAO account = mock(AccountDAO.class, Mockito.RETURNS_SMART_NULLS);
        Account account1 = account.findAccount("x", "y");
        System.out.println(account1);

        Account account2 = accountDAO.findAccount("x", "y");
        System.out.println(account2);
    }
}
