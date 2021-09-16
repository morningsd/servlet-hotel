package edu.demian.wp.web.action.get;

import edu.demian.wp.model.entity.Application;
import edu.demian.wp.model.service.ApplicationService;
import edu.demian.wp.model.service.impl.ApplicationServiceImpl;
import edu.demian.wp.web.action.Action;
import edu.demian.wp.web.action.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetViewApplicationsAction implements Action {
    private final ApplicationService applicationService = new ApplicationServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        List<Application> applicationList = applicationService.findAll();

        request.setAttribute("applicationList", applicationList);

        return "/manager/viewApplications";
    }
}
