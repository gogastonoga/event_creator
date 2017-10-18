package com.capgemini.wolimierz.userregistry.repository;

import com.capgemini.wolimierz.userregistry.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {
    User findById(long id);

    User findByGlobalId(UUID globalId);

    User findByEmail(String email);
}
