package com.capgemini.wolimierz.userregistry.service;

import com.capgemini.wolimierz.userregistry.UserCreateDto;
import com.capgemini.wolimierz.userregistry.model.User;

import java.util.Collection;
import java.util.Optional;

public interface UserService {
    Optional<User> getUserById(long id);

    Optional<User> getUserByEmail(String email);

    Collection<User> getAllUsers();

    User create(UserCreateDto userCreateDto);
}
