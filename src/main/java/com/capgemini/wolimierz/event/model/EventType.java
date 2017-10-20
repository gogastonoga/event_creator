package com.capgemini.wolimierz.event.model;

import com.capgemini.wolimierz.controller.dto.EventTypeDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "EVENT_TYPES")
public class EventType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "TRANSLATION", nullable = false)
    private String translation;
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;
    @Column(name = "GLOBAL_ID", nullable = false, unique = true)
    private UUID globalId;
    @Column(name = "PRICE")
    private double price;


    public EventType(String translation, String description, double price) {
        this.translation = translation;
        this.description = description;
        this.globalId = UUID.randomUUID();
        this.price = price;
    }

    public void updateFrom(EventTypeDto dto) {
        this.description = dto.getDescription();
        this.translation = dto.getName();
    }
}
