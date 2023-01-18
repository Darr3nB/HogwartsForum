package com.HogwartsForum.model;

public enum Roles {
    ADMIN,
    USER,
    VISITOR;

    public static Roles getRoleByStringEquivalent(String role) {
        return role.equals("Admin") ? ADMIN : role.equals("User") ? USER : VISITOR;
    }
}
