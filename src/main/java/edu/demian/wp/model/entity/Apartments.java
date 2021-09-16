package edu.demian.wp.model.entity;

import java.math.BigDecimal;

public class Apartments extends Entity {
    private long id;
    private int noRooms;
    private int noAdults;
    private int noChildren;
    private BigDecimal price;
    private long accountId;
    private int statusId;
    private int typeId;

    private String imagePath;

    private Status status;
    private Type type;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Apartments{" +
                "id=" + id +
                ", noRooms=" + noRooms +
                ", noAdults=" + noAdults +
                ", noChildren=" + noChildren +
                ", price=" + price +
                ", accountId=" + accountId +
                ", statusId=" + statusId +
                ", typeId=" + typeId +
                ", imagePath='" + imagePath + '\'' +
                ", status=" + status +
                ", type=" + type +
                '}';
    }
}
