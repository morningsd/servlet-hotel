package edu.demian.wp.model.service.impl;

import edu.demian.wp.model.dao.BillDao;
import edu.demian.wp.model.dao.ApartmentsDao;
import edu.demian.wp.model.dao.impl.BillDaoImpl;
import edu.demian.wp.model.dao.impl.ApartmentsDaoImpl;
import edu.demian.wp.model.entity.Bill;
import edu.demian.wp.model.entity.Apartments;
import edu.demian.wp.model.entity.State;
import edu.demian.wp.model.entity.Status;
import edu.demian.wp.model.service.BillService;

import java.util.List;

public class BillServiceImpl implements BillService {
    private final BillDao billDao = new BillDaoImpl();
    private final ApartmentsDao roomDao = new ApartmentsDaoImpl();

    @Override
    public List<Bill> findAll() {
        List<Bill> billList = billDao.findAll();
        setRoomForBills(billList);
        return billList;
    }

    @Override
    public List<Bill> findAccountBills(long id) {
        List<Bill> billList = billDao.findAccountBills(id);
        setRoomForBills(billList);
        return billList;
    }

    @Override
    public void save(Bill bill) {
        Apartments room = roomDao.find(bill.getApartmentsId());
        bill.setRoom(room);
        room.setStatusId(Status.getStatusId("BOOKED"));
        roomDao.updateStatusId(room);
        billDao.save(bill);
    }

    @Override
    public void payBill(long id) {
        Bill bill = billDao.find(id);
        bill.setStateId(State.getStateId("PAID"));
        billDao.updateStateId(bill);
    }

    private void setRoomForBills(List<Bill> billList) {
        for (Bill bill : billList) {
            bill.setRoom(roomDao.find(bill.getApartmentsId()));
        }
    }

}
