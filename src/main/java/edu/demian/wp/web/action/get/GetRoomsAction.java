package edu.demian.wp.web.action.get;

import edu.demian.wp.model.entity.Apartments;
import edu.demian.wp.model.entity.Status;
import edu.demian.wp.model.entity.Type;
import edu.demian.wp.model.service.ApartmentsService;
import edu.demian.wp.model.service.impl.ApartmentsServiceImpl;
import edu.demian.wp.web.action.Action;
import edu.demian.wp.web.action.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

public class GetRoomsAction implements Action {
    private final ApartmentsService apartmentsService = new ApartmentsServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {

        List<Apartments> apartmentsList = apartmentsService.findAll();

        List<Type> typeList = Arrays.asList(Type.values());
        List<Status> statusList = Arrays.asList(Status.values());

        HttpSession session = request.getSession();

        session.setAttribute("typeList", typeList);
        session.setAttribute("statusList", statusList);
        request.setAttribute("roomList", apartmentsList);

        return "/rooms";
    }
}
