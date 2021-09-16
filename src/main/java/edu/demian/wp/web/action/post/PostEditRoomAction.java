package edu.demian.wp.web.action.post;

import edu.demian.wp.model.entity.Apartments;
import edu.demian.wp.model.entity.Status;
import edu.demian.wp.model.entity.Type;
import edu.demian.wp.model.service.ApartmentsService;
import edu.demian.wp.model.service.impl.ApartmentsServiceImpl;
import edu.demian.wp.web.action.Action;
import edu.demian.wp.web.action.ActionException;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

@MultipartConfig
public class PostEditRoomAction implements Action {
    private final ApartmentsService roomService = new ApartmentsServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        String[] checkbox = request.getParameterValues("keep-image");
        boolean keepImage = checkbox != null;
        // TODO edit to new db format
//        Apartments room = new Apartments();
//        room.setId(Long.parseLong(request.getParameter("room_id")));
//        room.setPeopleCapacity(Integer.parseInt(request.getParameter("quantity")));
//        room.setPrice(new BigDecimal(request.getParameter("price")));
//        room.setTypeId(Type.getTypeId(request.getParameter("types")));
//        room.setStatusId(Status.getStatusId(request.getParameter("statuses")));
//        if (!keepImage) {
//            room.setImagePath(roomService.changeImage(room.getId(), request));
//        } else {
//            room.setImagePath(roomService.getRoomImage(room.getId()));
//        }
//        roomService.update(room);

        return "redirect:/rooms";
    }

}
