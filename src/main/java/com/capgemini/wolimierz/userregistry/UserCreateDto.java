package com.capgemini.wolimierz.userregistry;

import com.capgemini.wolimierz.userregistry.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class UserCreateDto {
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String surname;
    @NotNull
    @NotEmpty
    private String password;
    @NotNull
    @NotEmpty
    @Email
    private String email;
    @NotNull
    private Role role;
}
