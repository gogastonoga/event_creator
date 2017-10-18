package com.capgemini.wolimierz.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrganizerDto {
    private String name;
    private String surname;
    private String phoneNumber;
    private String mail;
}
