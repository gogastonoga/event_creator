package com.capgemini.wolimierz.event.service;

import com.capgemini.wolimierz.controller.dto.CreateEventDto;
import com.capgemini.wolimierz.event.model.Event;

import java.util.List;
import java.util.UUID;

public interface EventService {
    Event createEvent(CreateEventDto createEventDto);

    List<Event> findEvents();

    Event findEvent(UUID globalId);
}
