package com.zelda.model;

import lombok.Builder;

import java.time.Instant;

@Builder(toBuilder = true)
public record RefreshToken (
        Long id,
        User user,
        String token,
        Instant createdAt,
        Instant updatedAt
){
}
