package edu.demian.wp.web.action.post;

import edu.demian.wp.model.service.ApplicationService;
import edu.demian.wp.model.service.impl.ApplicationServiceImpl;
import edu.demian.wp.web.action.Action;
import edu.demian.wp.web.action.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostCancelApplicationAction implements Action {
    private final ApplicationService applicationService = new ApplicationServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        applicationService.delete(Long.parseLong(request.getParameter("application_id")));

        return "redirect:/client/cabinet";
    }
}
