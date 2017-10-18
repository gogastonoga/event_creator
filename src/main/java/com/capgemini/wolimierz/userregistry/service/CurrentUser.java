package com.capgemini.wolimierz.userregistry.service;

import com.capgemini.wolimierz.userregistry.model.Role;
import com.capgemini.wolimierz.userregistry.model.User;
import lombok.Getter;
import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUser extends org.springframework.security.core.userdetails.User {
    @Getter
    private User user;

    public CurrentUser(User user) {
        super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
        this.user = user;
    }

    public Long getId() {
        return user.getId();
    }

    public Role getRole() {
        return user.getRole();
    }
}
