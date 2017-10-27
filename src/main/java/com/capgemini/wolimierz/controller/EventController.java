package com.capgemini.wolimierz.controller;

import com.capgemini.wolimierz.controller.dto.CreateEventDto;
import com.capgemini.wolimierz.event.dto.OfferDto;
import com.capgemini.wolimierz.event.model.Event;
import com.capgemini.wolimierz.event.service.EventService;
import com.capgemini.wolimierz.utils.EnvironmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200", "10.42.96.238:4200"})
@RestController
@RequestMapping("/wolimierz/events")
public class EventController extends BaseController{

    private final EventService eventService;
    private final EnvironmentService environmentService;

    @Autowired
    public EventController(EventService eventService, EnvironmentService environmentService) {
        this.eventService = eventService;
        this.environmentService = environmentService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public OfferDto createEvent(@RequestBody @Valid CreateEventDto createEventDto) {
        String mediaUrl = environmentService.getMediaBasicUrl();
        return new OfferDto(eventService.createEvent(createEventDto), mediaUrl);
    }

    @PreAuthorize(ADMIN_OR_STAFF)
    @RequestMapping(method = RequestMethod.GET)
    public List<OfferDto> getEvents() {
        String mediaUrl = environmentService.getMediaBasicUrl();
        return eventService.findEvents().stream()
                .map(event -> new OfferDto(event, mediaUrl))
                .collect(Collectors.toList());
    }

    @PreAuthorize(ADMIN_OR_STAFF)
    @RequestMapping(method = RequestMethod.GET, params = "id")
    public OfferDto findEvent(@RequestParam(name = "id") UUID globalId) {
        Optional<Event> event = Optional.ofNullable(eventService.findEvent(globalId));
        String mediaUrl = environmentService.getMediaBasicUrl();
        return new OfferDto(event.orElseThrow(() ->
                new IllegalArgumentException(String.format("Event with id: %s not found", globalId))),
                mediaUrl);
    }
}
