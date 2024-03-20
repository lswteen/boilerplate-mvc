package com.zelda.model;

import com.zelda.type.UserType;
import lombok.Builder;


@Builder(toBuilder = true)
public record User(
        Long id,
        String userId,
        String name,
        String password,
        UserType idType,
        String idValue
) {
}
