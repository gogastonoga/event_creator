package com.capgemini.wolimierz.contactrequest.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Entity
@Table(name = "CONTACT_REQUESTS")
@NoArgsConstructor
@Getter
public class ContactRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "GLOBAL_ID", nullable = false, unique = true)
    private UUID globalId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "TOPIC")
    private String topic;

    @Column(name = "CREATOR_EMAIL")
    private String creatorEmail;

    @Column(name = "WAS_READ")
    private Boolean wasRead;

    @Column(name = "CREATION_TIME")
    private LocalDateTime creationTime;

    public ContactRequest(String message, String creatorEmail, String name, String surname, String topic) {
        this.message = message;
        this.creatorEmail = creatorEmail;
        globalId = UUID.randomUUID();
        wasRead = Boolean.FALSE;
        this.name = name;
        this.surname = surname;
        this.topic = topic;
        creationTime = LocalDateTime.now(ZoneId.of("UTC"));
    }

    public void setWasRead() {
        this.wasRead = Boolean.TRUE;
    }
}
