package edu.demian.wp.web.action.post;

import edu.demian.wp.model.entity.Application;
import edu.demian.wp.model.entity.Type;
import edu.demian.wp.model.service.impl.ApplicationServiceImpl;
import edu.demian.wp.web.action.Action;
import edu.demian.wp.web.action.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PostAddApplicationAction implements Action {
    private final ApplicationServiceImpl applicationService = new ApplicationServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yy HH:mm");
        // TODO edit to new db format
//        Application application = new Application();
//        application.setQuantity(Integer.parseInt(request.getParameter("quantity")));
//        application.setStartDate(LocalDateTime.parse(request.getParameter("start_date"), formatter));
//        application.setEndDate(LocalDateTime.parse(request.getParameter("end_date"), formatter));
//        application.setMinPrice(new BigDecimal(request.getParameter("min-price")));
//        application.setMaxPrice(new BigDecimal(request.getParameter("max-price")));
//        application.setWishes(request.getParameter("wishes"));
//        application.setTypeId(Type.getTypeId(request.getParameter("types")));
//        application.setAccountId(Long.parseLong(request.getParameter("account_id")));

        //applicationService.addApplication(application);

        return "redirect:/rooms";
    }
}
