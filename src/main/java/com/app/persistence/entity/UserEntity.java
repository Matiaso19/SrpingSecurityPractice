package com.app.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(name = "is_Enabled")
    private Boolean isEnabled;

    @Column(name = "account_No_Expired")
    private Boolean accountNoExpired;

    @Column(name = "account_No_Locked")
    private Boolean accountNoLocked;

    @Column(name = "credentials_No_Expired")
    private Boolean credentialsNoExpired;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name =
    "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();
}
