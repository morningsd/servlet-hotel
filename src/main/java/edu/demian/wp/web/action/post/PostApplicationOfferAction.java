package edu.demian.wp.web.action.post;

import edu.demian.wp.model.service.ApplicationService;
import edu.demian.wp.model.service.impl.ApplicationServiceImpl;
import edu.demian.wp.web.action.Action;
import edu.demian.wp.web.action.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostApplicationOfferAction implements Action {
    private final ApplicationService applicationService = new ApplicationServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        applicationService.offerApplication(Long.parseLong(request.getParameter("application_id")),
                Long.parseLong(request.getParameter("room_id")));

        return "redirect:/manager/viewApplications";
    }
}
