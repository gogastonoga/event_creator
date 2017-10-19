package com.capgemini.wolimierz.controller;

import com.capgemini.wolimierz.userregistry.UserCreateDto;
import com.capgemini.wolimierz.userregistry.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/wolimierz/users")
@CrossOrigin(origins = {"http://localhost:4200", "10.42.96.238:4200"})
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public void createUser(@RequestBody UserCreateDto userCreateDto) {
        userService.create(userCreateDto);
    }
}
