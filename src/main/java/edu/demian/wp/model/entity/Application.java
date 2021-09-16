package edu.demian.wp.model.entity;

import java.time.LocalDateTime;

public class Application extends Entity {
    private long id;
    private int noRooms;
    private int noAdults;
    private int noChildren;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private String wishes;
    private int typeId;
    private long accountId;
    private long apartmentsId;

    private Type type;
    private Apartments apartments;

    public void setType(Type type) {
        this.type = type;
    }

    public Apartments getApartments() {
        return apartments;
    }

    public void setApartments(Apartments apartments) {
        this.apartments = apartments;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNoRooms() {
        return noRooms;
    }

    public void setNoRooms(int noRooms) {
        this.noRooms = noRooms;
    }

    public int getNoAdults() {
        return noAdults;
    }

    public void setNoAdults(int noAdults) {
        this.noAdults = noAdults;
    }

    public int getNoChildren() {
        return noChildren;
    }

    public void setNoChildren(int noChildren) {
        this.noChildren = noChildren;
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

    public String getWishes() {
        return wishes;
    }

    public void setWishes(String wishes) {
        this.wishes = wishes;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
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

    public Type getType() {
        return type;
    }

    public Apartments getRoom() {
        return apartments;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", noRooms=" + noRooms +
                ", noAdults=" + noAdults +
                ", noChildren=" + noChildren +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", wishes='" + wishes + '\'' +
                ", typeId=" + typeId +
                ", accountId=" + accountId +
                ", apartmentsId=" + apartmentsId +
                ", type=" + type +
                ", apartments=" + apartments +
                '}';
    }
}
