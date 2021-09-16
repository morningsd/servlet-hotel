package edu.demian.wp.web.action.post;

import edu.demian.wp.model.entity.Account;
import edu.demian.wp.model.entity.Role;
import edu.demian.wp.model.service.AccountService;
import edu.demian.wp.model.service.impl.AccountServiceImpl;
import edu.demian.wp.web.action.Action;
import edu.demian.wp.web.action.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PostLoginAction implements Action {
    private final AccountService accountService = new AccountServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();

        Account account = accountService.login(request.getParameter("email"), request.getParameter("password"));

        session.setAttribute("account", account);
        Role accountRole = Role.getRole(account);
        session.setAttribute("accountRole", accountRole);

        return "redirect:/home";
    }
}
