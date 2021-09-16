package edu.demian.wp.web.action.get;

import edu.demian.wp.model.entity.Account;
import edu.demian.wp.model.entity.Application;
import edu.demian.wp.model.entity.Bill;
import edu.demian.wp.model.service.ApplicationService;
import edu.demian.wp.model.service.BillService;
import edu.demian.wp.model.service.impl.ApplicationServiceImpl;
import edu.demian.wp.model.service.impl.BillServiceImpl;
import edu.demian.wp.web.action.Action;
import edu.demian.wp.web.action.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetCabinetAction implements Action {
    private final BillService billService = new BillServiceImpl();
    private final ApplicationService applicationService = new ApplicationServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        Account account = (Account) request.getSession().getAttribute("account");
        List<Bill> billList = billService.findAccountBills(account.getId());
        List<Application> applicationList = applicationService.findAccountApplications(account.getId());

        request.setAttribute("applicationList", applicationList);
        request.setAttribute("billList", billList);

        return "/client/cabinet";
    }
}
