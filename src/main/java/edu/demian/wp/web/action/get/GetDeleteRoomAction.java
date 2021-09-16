package edu.demian.wp.web.action.get;

import edu.demian.wp.model.service.ApartmentsService;
import edu.demian.wp.model.service.impl.ApartmentsServiceImpl;
import edu.demian.wp.web.action.Action;
import edu.demian.wp.web.action.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetDeleteRoomAction implements Action {
    private final ApartmentsService apartmentsService = new ApartmentsServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        apartmentsService.delete(Long.parseLong(request.getParameter("room_id")));

        return "redirect:/rooms";
    }
}
