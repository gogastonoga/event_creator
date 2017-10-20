package com.capgemini.wolimierz.controller.dto;

import com.capgemini.wolimierz.event.model.Organizer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrganizerDto {
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String mail;

    public static OrganizerDto from(Organizer organizer) {
        return new OrganizerDto(organizer.getName(), organizer.getSurname(), organizer.getPhoneNumber(), organizer.getMail());
    }
}
