package edu.demian.wp.model.service;

import edu.demian.wp.model.entity.Bill;

import java.util.List;

public interface BillService {
    List<Bill> findAll();
    List<Bill> findAccountBills(long id);

    void save(Bill bill);

    void payBill(long id);
}
