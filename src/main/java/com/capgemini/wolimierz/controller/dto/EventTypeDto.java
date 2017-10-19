package com.capgemini.wolimierz.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class EventTypeDto extends DescriptionDto {
    private UUID globalId;
    private String name;

    public EventTypeDto(String description, String name, UUID globalId) {
        super(description);
        this.name = name;
        this.globalId = globalId;
    }
}
