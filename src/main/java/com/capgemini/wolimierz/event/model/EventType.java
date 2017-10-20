package com.capgemini.wolimierz.event.model;

import com.capgemini.wolimierz.controller.dto.EventTypeDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "EVENT_TYPES")
public class EventType implements Cost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "TRANSLATION", nullable = false)
    private String translation;
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;
    @Column(name = "GLOBAL_ID", nullable = false, unique = true)
    private UUID globalId;
    @DecimalMin("0.0")
    @Setter
    @Column(name = "COST_FACTOR")
    private double costFactor;


    public EventType(String translation, String description, double price) {
        this.translation = translation;
        this.description = description;
        this.globalId = UUID.randomUUID();
        this.costFactor = price;
    }

    public void updateFrom(EventTypeDto dto) {
        this.description = dto.getDescription();
        this.translation = dto.getName();
    }

    @Override
    public String getName() {
        return translation;
    }
}
