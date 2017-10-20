package com.capgemini.wolimierz.userregistry.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @Setter
    @NotNull
    @Column(name = "SURNAME", nullable = false)
    private String surname;

    @Setter
    @NotNull
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Setter
    @NotNull
    @Column(name = "EMAIL", nullable = false)
    private String email;

    @NotNull
    @Column(name = "ROLE", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role = Role.ADMIN;//TODO check if there will be only 'admin' user in the system

    @Column(name = "GLOBAL_ID", nullable = false, unique = true)
    private UUID globalId;

    public User() {
        globalId = UUID.randomUUID();
    }
}
