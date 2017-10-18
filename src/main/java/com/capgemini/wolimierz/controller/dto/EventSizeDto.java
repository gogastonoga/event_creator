package com.capgemini.wolimierz.controller.dto;

import lombok.Getter;

import java.util.UUID;

@Getter
public class EventSizeDto extends DescriptionDto {
    private UUID globalId;
    private String imageUrl;

    public EventSizeDto(String description, String imageUrl, UUID globalId) {
        super(description);
        this.globalId = globalId;
    }

}
