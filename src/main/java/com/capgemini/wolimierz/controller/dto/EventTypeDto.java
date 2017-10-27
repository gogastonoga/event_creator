package com.capgemini.wolimierz.controller.dto;

import com.capgemini.wolimierz.EntityDtoWithGlobalId;
import com.capgemini.wolimierz.event.model.EventType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class EventTypeDto extends DescriptionDto implements EntityDtoWithGlobalId {
    private UUID globalId;
    // @NotNull
    private String name;

    public EventTypeDto(String description, String name, UUID globalId) {
        super(description);
        this.name = name;
        this.globalId = globalId;
    }

    public static EventTypeDto from(EventType eventType) {
        return new EventTypeDto(eventType.getDescription(), eventType.getTranslation(), eventType.getGlobalId());
    }
}
