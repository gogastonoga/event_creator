package com.capgemini.wolimierz.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoggedUserDto {
    private String email;
    private UUID globalId;
    private String token;
}
