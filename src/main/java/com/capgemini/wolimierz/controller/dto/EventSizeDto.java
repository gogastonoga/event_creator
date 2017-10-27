package com.capgemini.wolimierz.controller.dto;

import com.capgemini.wolimierz.EntityDtoWithGlobalId;
import com.capgemini.wolimierz.event.model.EventSize;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class EventSizeDto extends DescriptionDto implements EntityDtoWithGlobalId {
    private UUID globalId;
    private String imageUrl;

    public EventSizeDto(String description, UUID globalId, String imageUrl) {
        super(description);
        this.globalId = globalId;
        this.imageUrl = imageUrl;
    }

    public static EventSizeDto from(EventSize eventSize, String mediaUrl) {
        return new EventSizeDto(eventSize.getDescription(),
                eventSize.getGlobalId(), eventSize.getImage() == null ?
                null : mediaUrl + eventSize.getImage().getGlobalId());
    }

}
