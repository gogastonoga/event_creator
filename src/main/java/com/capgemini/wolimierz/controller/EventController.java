package com.capgemini.wolimierz.controller;

import com.capgemini.wolimierz.controller.dto.CreateEventDto;
import com.capgemini.wolimierz.event.dto.OfferDto;
import com.capgemini.wolimierz.event.service.EventService;
import com.capgemini.wolimierz.event.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "10.42.96.238:4200"})
@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public OfferDto createEvent(@RequestBody CreateEventDto createEventDto) {
        return new OfferDto(eventService.createEvent(createEventDto));
    }

  //  @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public List<Event> getEvents() {
        return eventService.findEvents();
    }
}
