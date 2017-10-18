package com.capgemini.wolimierz.event.dto;

import com.capgemini.wolimierz.controller.dto.DescriptionDto;
import com.capgemini.wolimierz.event.model.EventType;
import lombok.Getter;

import java.util.UUID;

@Getter
public class EventDto extends DescriptionDto {
    private String translation;
    private UUID globalId;
    private boolean selected = false;

    public EventDto(EventType type) {
        super(type.getDescription());
        this.translation = type.getTranslation();
        this.globalId = type.getGlobalId();
    }
}
