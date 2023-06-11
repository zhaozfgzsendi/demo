package com.example.mockito.quickstart;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountLoginControllerTest {

    private AccountDAO accountDAO;

    private HttpServletRequest request;

    private AccountLoginController accountLoginController;


    @Before
    public void setUp() {
        this.accountDAO = Mockito.mock(AccountDAO.class);
        this.request = Mockito.mock(HttpServletRequest.class);
        this.accountLoginController = new AccountLoginController(accountDAO);
    }

    @Test
    public void testLoginSuccess() {
        Account account = new Account();
        when(request.getParameter("username")).thenReturn("admin");
        when(request.getParameter("password")).thenReturn("123456");

        when(accountDAO.findAccount(anyString(), anyString())).thenReturn(account);

        assertThat(accountLoginController.login(request), equalTo("/index"));
    }

    @Test
    public void testLoginFailure() {
        Account account = new Account();
        when(request.getParameter("username")).thenReturn("admin");
        when(request.getParameter("password")).thenReturn("123456");

        when(accountDAO.findAccount(anyString(), anyString())).thenReturn(null);

        assertThat(accountLoginController.login(request), equalTo("/login"));
    }

    @Test
    public void testLogin505() {
        Account account = new Account();
        when(request.getParameter("username")).thenReturn("admin");
        when(request.getParameter("password")).thenReturn("123456");
//        when(accountDAO.findAccount(anyString(), anyString())).thenReturn(null);
        when(accountDAO.findAccount(anyString(), anyString())).thenThrow(UnsupportedOperationException.class);
        assertThat(accountLoginController.login(request), equalTo("/505"));
    }
}