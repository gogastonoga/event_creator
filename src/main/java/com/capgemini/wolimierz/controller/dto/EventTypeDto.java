package com.capgemini.wolimierz.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class EventTypeDto extends DescriptionDto {
    private UUID globalId;
   // @NotNull
    private String name;

    public EventTypeDto(String description, String name, UUID globalId) {
        super(description);
        this.name = name;
        this.globalId = globalId;
    }
}
