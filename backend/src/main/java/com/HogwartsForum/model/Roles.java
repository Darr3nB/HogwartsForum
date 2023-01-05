package com.HogwartsForum.model;

public enum Roles {
    ADMIN,
    USER;

    public static Roles getRoleByStringEquivalent(String role) {
        return role.equals("Admin") ? ADMIN : USER;
    }
}
