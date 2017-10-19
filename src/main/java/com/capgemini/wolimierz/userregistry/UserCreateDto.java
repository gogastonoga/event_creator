package com.capgemini.wolimierz.userregistry;

import com.capgemini.wolimierz.userregistry.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserCreateDto {
    private String name;
    private String surname;
    private String password;
    private String email;
    private Role role;
}
