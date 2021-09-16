package edu.demian.wp.model.entity;

public enum Role {
    CLIENT, MANAGER;

    public static Role getRole(Account account) {
        int roleId = account.getRoleId();
        return Role.values()[roleId - 1];
    }

    public String getName() {
        return name().toLowerCase();
    }
}
