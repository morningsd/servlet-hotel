package edu.demian.wp.web.action.post;

import edu.demian.wp.model.service.BillService;
import edu.demian.wp.model.service.impl.BillServiceImpl;
import edu.demian.wp.web.action.Action;
import edu.demian.wp.web.action.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostPayBillAction implements Action {
    private final BillService billService = new BillServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        billService.payBill(Long.parseLong(request.getParameter("bill_id")));

        return "redirect:/client/cabinet";
    }
}
