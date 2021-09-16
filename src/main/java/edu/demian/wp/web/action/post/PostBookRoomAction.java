package edu.demian.wp.web.action.post;

import edu.demian.wp.model.entity.Bill;
import edu.demian.wp.model.service.BillService;
import edu.demian.wp.model.service.impl.BillServiceImpl;
import edu.demian.wp.web.action.Action;
import edu.demian.wp.web.action.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.ChronoUnit.DAYS;

public class PostBookRoomAction implements Action {
    private final BillService billService = new BillServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yy HH:mm");

        LocalDateTime startDateTime = LocalDateTime.parse(request.getParameter("start_date"), formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(request.getParameter("end_date"), formatter);

        Bill bill = new Bill();
        bill.setAccountId(Long.parseLong(request.getParameter("account_id")));
        bill.setApartmentsId(Long.parseLong(request.getParameter("room_id")));
        bill.setCheckIn(startDateTime);
        bill.setCheckOut(endDateTime);
        bill.setTotal(new BigDecimal(request.getParameter("room_price")).multiply(BigDecimal.valueOf(DAYS.between(startDateTime, endDateTime))));

        billService.save(bill);

        return "redirect:/client/cabinet";
    }
}
