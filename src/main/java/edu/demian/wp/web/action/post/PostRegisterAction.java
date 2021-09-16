package edu.demian.wp.web.action.post;

import edu.demian.wp.model.entity.Account;
import edu.demian.wp.model.service.AccountService;
import edu.demian.wp.model.service.impl.AccountServiceImpl;
import edu.demian.wp.web.action.Action;
import edu.demian.wp.web.action.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostRegisterAction implements Action {
    private final AccountService accountService = new AccountServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        String password = request.getParameter("password");

        Account account = new Account();
        account.setFirstName(request.getParameter("fname"));
        account.setLastName(request.getParameter("lname"));
        account.setEmail(request.getParameter("email"));

        accountService.validateRegister(account);
        accountService.register(account, password);

        return "redirect:/login";

    }
}
