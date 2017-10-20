package com.capgemini.wolimierz.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class EventSizesContainer {
    @NotNull
    private List<EventSizeDto> eventSizes = new ArrayList<>();
}
