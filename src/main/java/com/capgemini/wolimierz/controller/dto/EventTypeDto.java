package com.capgemini.wolimierz.controller.dto;

import lombok.Getter;

import java.util.UUID;

@Getter
public class EventTypeDto extends DescriptionDto {
    private UUID globalId;

    public EventTypeDto(String description, UUID globalId) {
        super(description);
        this.globalId = globalId;
    }
}
