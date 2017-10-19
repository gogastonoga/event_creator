package com.capgemini.wolimierz.contactrequest.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "CREATOR_EMAIL")
    private String creatorEmail;

    @Column(name = "WAS_READ")
    private Boolean wasRead;

    public ContactRequest(String message, String creatorEmail) {
        this.message = message;
        this.creatorEmail = creatorEmail;
        globalId = UUID.randomUUID();
        wasRead = Boolean.FALSE;
    }

    public void setWasRead() {
        this.wasRead = Boolean.TRUE;
    }
}
