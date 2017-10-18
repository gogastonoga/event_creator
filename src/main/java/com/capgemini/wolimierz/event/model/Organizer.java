package com.capgemini.wolimierz.event.model;

import com.capgemini.wolimierz.controller.dto.OrganizerDto;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Organizer {
    @Column(name = "ORGANIZER_NAME", nullable = false)
    private String name;
    @Column(name = "ORGANIZER_SURNAME", nullable = false)
    private String surname;
    @Column(name = "ORGANIZER_PHONE_NUMBER", nullable = false)
    private String phoneNumber;
    @Column(name = "ORGANIZER_MAIL", nullable = false)
    private String mail;

    public static Organizer from(OrganizerDto organizer) {
        return new Organizer(organizer.getName(), organizer.getSurname(), organizer.getPhoneNumber(), organizer.getMail());
    }
}
