package edu.demian.wp.model.entity;

public enum State {
    NEW, PAID;

    public static State getState(int id) {
        return State.values()[id-1];
    }

    public static int getStateId(String state) {
        for (int i = 0; i < State.values().length; i++) {
            if (State.values()[i].name().equals(state)) {
                return i+1;
            }
        }
        return 0;
    }

    public String getName() {
        return name().toLowerCase();
    }
}
