package com.example.mockito.quickstart;

import jakarta.servlet.http.HttpServletRequest;

public class AccountLoginController {

    private final AccountDAO accountDAO;

    public AccountLoginController(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public String login(HttpServletRequest request) {
        final String useraname = request.getParameter("username");
        final String password = request.getParameter("password");
        try {
            Account account = accountDAO.findAccount(useraname, password);
            if (account == null) {
                return "/login";
            } else {
                return "/index";
            }
        } catch (Exception e) {
            return "/505";
        }
    }
}
