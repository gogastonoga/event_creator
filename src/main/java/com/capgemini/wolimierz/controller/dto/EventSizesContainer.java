package com.capgemini.wolimierz.controller.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class EventSizesContainer {
    private List<EventSizeDto> eventSizes = new ArrayList<>();
}
