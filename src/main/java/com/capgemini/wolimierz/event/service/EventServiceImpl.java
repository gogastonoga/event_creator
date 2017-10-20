package com.capgemini.wolimierz.event.service;

import com.capgemini.wolimierz.controller.dto.CreateEventDto;
import com.capgemini.wolimierz.cost.service.CostSettingService;
import com.capgemini.wolimierz.event.model.*;
import com.capgemini.wolimierz.event.repository.EventRepository;
import com.capgemini.wolimierz.event.repository.EventSizeRepository;
import com.capgemini.wolimierz.event.repository.EventTypeRepository;
import com.capgemini.wolimierz.event.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
//java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8008 -jar yourapplication.jar

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventSizeRepository eventSizeRepository;
    private final EventTypeRepository eventTypeRepository;
    private final SeasonRepository seasonRepository;
    private final CostSettingService costSettingService;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, EventSizeRepository eventSizeRepository,
                            EventTypeRepository eventTypeRepository, SeasonRepository seasonRepository, CostSettingService costSettingService) {
        this.eventRepository = eventRepository;
        this.eventSizeRepository = eventSizeRepository;
        this.eventTypeRepository = eventTypeRepository;
        this.seasonRepository = seasonRepository;
        this.costSettingService = costSettingService;
    }

    public Event createEvent(CreateEventDto createEventDto) {
        List<EventType> eventTypes = eventTypeRepository.findAllByGlobalIdIn(createEventDto.getEventTypeIds());
        EventSize eventSize = Optional.ofNullable(eventSizeRepository.findByGlobalId(createEventDto.getEventSizeId()))
                .orElseThrow(() -> new IllegalArgumentException(String.format("Size with id %s not found", createEventDto.getEventSizeId())));
        Season season = Optional.ofNullable(seasonRepository.findByGlobalId(createEventDto.getSeasonId()))
                .orElseThrow(() -> new IllegalArgumentException(String.format("Season with id %s not found", createEventDto.getEventSizeId())));

        Event event = new Event(
                null,
                Organizer.from(createEventDto.getOrganizer()),
                createEventDto.getEventTime(),
                eventTypes,
                createEventDto.getRooms(),
                createEventDto.getGuestsNumber(),
                eventSize,
                createEventDto.getNights(),
                createEventDto.getAdditionalRequirements(),
                createEventDto.getMaxCost(),
                createEventDto.getKindOfDays(),
                season,
                UUID.randomUUID(),
                costSettingService.calculateCost(createEventDto.getNights(),
                        createEventDto.getRooms(),
                        createEventDto.getGuestsNumber(),
                        createEventDto.getKindOfDays()),
                costSettingService.calculateDetailedCost(createEventDto.getNights(),
                        createEventDto.getRooms(),
                        createEventDto.getGuestsNumber(),
                        createEventDto.getKindOfDays(),
                        eventTypes, eventSize, season)
        );
        return eventRepository.save(event);
    }

    @Override
    public List<Event> findEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event findEvent(UUID globalId) {
        return eventRepository.findByGlobalId(globalId);
    }

}
