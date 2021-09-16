package edu.demian.wp.model.entity;

public enum Type {
    ECONOMY, JUNIOR_SUITE, SUITE;

    public static Type getType(int id) {
        return Type.values()[id-1];
    }


    public static int getTypeId(String type) {
        for (int i = 0; i < Type.values().length; i++) {
            if (Type.values()[i].name().equals(type)) {
                return i+1;
            }
        }
        return 0;
    }

    public String getName() {
        return name().toLowerCase();
    }
}
