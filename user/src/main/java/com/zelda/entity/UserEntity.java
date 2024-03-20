package com.zelda.entity;



import com.zelda.type.UserRole;
import com.zelda.type.UserType;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Collection;
import java.util.Set;

@Builder
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity(name="users")
@Table(name="USERS")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_id")
    private String userId;

    @Column(name="name")
    private String name;

    @Column(name="password")
    private String password;

    @Column(name="id_type")
    private UserType idType;

    @Column(name="id_value")
    private String idValue;

    @Column(name="created_at")
    private Instant createdAt;

    @Column(name="updated_at")
    private Instant updatedAt;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles;

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
    private Collection<RefreshTokenEntity> refreshTokens;
}
