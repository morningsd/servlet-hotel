package edu.demian.wp.model.entity;

public enum Status {
    FREE, BOOKED, BUSY, INACCESSIBLE;

    public static Status getStatus(int id) {
        return Status.values()[id-1];
    }

    public static int getStatusId(String status) {
        for (int i = 0; i < Status.values().length; i++) {
            if (Status.values()[i].name().equals(status)) {
                return i+1;
            }
        }
        return 0;
    }

    public String getName() {
        return name().toLowerCase();
    }
}
