package com.zelda.type;

import lombok.Getter;

@Getter
public enum UserType {
    REG_NO("주민등록번호"),
    BUSINESS_NO("사업자번호");
    private final String description;
    UserType(String description){
        this.description = description;
    }
}
