package com.capgemini.wolimierz.event.service;

import com.capgemini.wolimierz.controller.dto.CreateEventDto;
import com.capgemini.wolimierz.event.repository.EventRepository;
import com.capgemini.wolimierz.event.model.Event;
import com.capgemini.wolimierz.event.model.Organizer;
import com.capgemini.wolimierz.event.repository.EventSizeRepository;
import com.capgemini.wolimierz.event.repository.EventTypeRepository;
import com.capgemini.wolimierz.event.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
//java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8008 -jar yourapplication.jar

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventSizeRepository eventSizeRepository;
    private final EventTypeRepository eventTypeRepository;
    private final SeasonRepository seasonRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, EventSizeRepository eventSizeRepository,
                            EventTypeRepository eventTypeRepository, SeasonRepository seasonRepository) {
        this.eventRepository = eventRepository;
        this.eventSizeRepository = eventSizeRepository;
        this.eventTypeRepository = eventTypeRepository;
        this.seasonRepository = seasonRepository;
    }

    public Event createEvent(CreateEventDto createEventDto) {
        Event event = new Event(
                null,
                Organizer.from(createEventDto.getOrganizer()),
                createEventDto.getEventTime(),
                eventTypeRepository.findAllByGlobalIdIn(createEventDto.getEventTypeIds()),
                createEventDto.getRooms(),
                createEventDto.getUsersNumber(),
                eventSizeRepository.findByGlobalId(createEventDto.getEventSizeId()),
                createEventDto.getNights(),
                createEventDto.getAdditionalRequirements(),
                createEventDto.getMaxCost(),
                createEventDto.getKindOfDays(),
                seasonRepository.findByGlobalId(createEventDto.getSeasonId()),
                UUID.randomUUID());
        return eventRepository.save(event);
    }

    @Override
    public List<Event> findEvents() {
        return eventRepository.findAll();
    }

}
