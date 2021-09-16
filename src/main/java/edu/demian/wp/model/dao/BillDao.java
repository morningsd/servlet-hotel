package edu.demian.wp.model.dao;

import edu.demian.wp.model.entity.Bill;

import java.util.List;

public interface BillDao {
    Bill find(long id);
    List<Bill> findAll();
    List<Bill> findAccountBills(long id);

    Bill save(Bill bill);
    void updateStateId(Bill bill);
}
