package com.capgemini.wolimierz.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EventTypesContainer {
    private List<EventTypeDto> eventTypes = new ArrayList<>();
}
