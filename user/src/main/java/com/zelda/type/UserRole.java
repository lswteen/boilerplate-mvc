package com.zelda.type;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("관리자")
    ,USER("회원");
    private final String description;
    UserRole(String description) {
        this.description = description;
    }
}
