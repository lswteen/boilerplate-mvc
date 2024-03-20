package com.zelda.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Builder
@Getter
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity(name="refreshTokens")
@Table(name="REFRESH_TOKENS")
public class RefreshTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_mapping_id", insertable = false, updatable = false)
    private UserEntity userEntity;

    @Column(name="token")
    private String token;

    @Column(name="created_at")
    @Builder.Default
    private Instant createdAt = Instant.now();

    @Column(name="updated_at")
    @Builder.Default
    private Instant updatedAt = Instant.now();
}
