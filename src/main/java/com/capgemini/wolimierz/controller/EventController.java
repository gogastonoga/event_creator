package com.capgemini.wolimierz.controller;

import com.capgemini.wolimierz.controller.dto.CreateEventDto;
import com.capgemini.wolimierz.event.dto.OfferDto;
import com.capgemini.wolimierz.event.model.Event;
import com.capgemini.wolimierz.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200", "10.42.96.238:4200"})
@RestController
@RequestMapping("/wolimierz/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public OfferDto createEvent(@RequestBody CreateEventDto createEventDto) {
        return new OfferDto(eventService.createEvent(createEventDto));
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('STAFF')")
    @RequestMapping(method = RequestMethod.GET)
    public List<OfferDto> getEvents() {
        return eventService.findEvents().stream()
                .map(OfferDto::new)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('STAFF')")
    @RequestMapping(method = RequestMethod.GET, params = "id")
    public OfferDto findEvent(@RequestParam(name = "id") UUID globalId) {
        Optional<Event> event = Optional.ofNullable(eventService.findEvent(globalId));
        return new OfferDto(event.orElseThrow(() -> new IllegalArgumentException(String.format("Event with id: %s not found", globalId))));
    }
}
