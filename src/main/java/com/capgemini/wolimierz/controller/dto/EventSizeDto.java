package com.capgemini.wolimierz.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class EventSizeDto extends DescriptionDto {
    private UUID globalId;
    private UUID imageId;

    public EventSizeDto(String description, UUID globalId, UUID imageId) {
        super(description);
        this.globalId = globalId;
        this.imageId = imageId;
    }

}
