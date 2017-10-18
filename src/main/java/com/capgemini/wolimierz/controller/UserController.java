package com.capgemini.wolimierz.controller;

import com.capgemini.wolimierz.controller.dto.LoggedUserDto;
import com.capgemini.wolimierz.controller.dto.LoginRequestDto;
import com.capgemini.wolimierz.userregistry.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/users")
@CrossOrigin(origins = {"http://localhost:4200", "10.42.96.238:4200"})
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

   /* @RequestMapping(path = "/login", method = RequestMethod.POST)
    public LoggedUserDto login(@RequestBody LoginRequestDto loginRequestDto) {

        return null;
    }*/
}
