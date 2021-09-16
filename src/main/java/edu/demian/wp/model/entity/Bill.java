package edu.demian.wp.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Bill extends Entity {
    // Add 1 because in db role values are saved from 1 index and ordinal value starts with 0
    private static final int BILL_STATE = State.NEW.ordinal() + 1;

    private long id;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private BigDecimal total;
    private long accountId;
    private long apartmentsId;
    private int stateId = BILL_STATE;

    private State state;
    private Apartments apartments;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Apartments getApartments() {
        return apartments;
    }

    public void setRoom(Apartments apartments) {
        this.apartments = apartments;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
        this.state = State.getState(stateId);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDateTime checkOut) {
        this.checkOut = checkOut;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getApartmentsId() {
        return apartmentsId;
    }

    public void setApartmentsId(long apartmentsId) {
        this.apartmentsId = apartmentsId;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", total=" + total +
                ", accountId=" + accountId +
                ", apartmentsId=" + apartmentsId +
                ", stateId=" + stateId +
                ", state=" + state +
                ", apartments=" + apartments +
                '}';
    }
}
