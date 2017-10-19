package com.capgemini.wolimierz.controller.dto;

import com.capgemini.wolimierz.event.model.Organizer;
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

    public static OrganizerDto from(Organizer organizer) {
        return new OrganizerDto(organizer.getName(), organizer.getSurname(), organizer.getPhoneNumber(), organizer.getMail());
    }
}
