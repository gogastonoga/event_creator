package com.capgemini.wolimierz.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class EventSizeDto extends DescriptionDto {
    private UUID globalId;
    private String imageUrl;

    public EventSizeDto(String description, UUID globalId, String imageUrl) {
        super(description);
        this.globalId = globalId;
        this.imageUrl = imageUrl;
    }

}
