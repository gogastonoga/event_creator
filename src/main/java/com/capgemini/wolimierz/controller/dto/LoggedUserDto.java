package com.capgemini.wolimierz.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class LoggedUserDto {
    private String email;
    private UUID globalId;
    private String token;
}
