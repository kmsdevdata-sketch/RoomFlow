package com.roomflow.domain.user;

public enum Role {
    USER("User_Role"),
    ADMIN("Admin_Role");

    private String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
