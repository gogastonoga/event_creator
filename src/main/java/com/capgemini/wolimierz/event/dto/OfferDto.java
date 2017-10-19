package com.capgemini.wolimierz.event.dto;

import com.capgemini.wolimierz.controller.dto.EventSizeDto;
import com.capgemini.wolimierz.controller.dto.EventTypeDto;
import com.capgemini.wolimierz.controller.dto.OrganizerDto;
import com.capgemini.wolimierz.event.model.Event;
import com.capgemini.wolimierz.event.model.KindOfDays;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class OfferDto {
    private OrganizerDto organizer;
    private LocalDateTime eventTime;
    private List<EventTypeDto> eventTypes = new ArrayList<>();
    private SeasonDto season;
    private KindOfDays kindOfDays;
    private int rooms;
    private int usersNumber;
    private EventSizeDto eventSize;
    private int nights;
    private String additionalRequirements;
    private double budget;
    private double estimatedCost;

    public OfferDto(Event event) {
        this.organizer = OrganizerDto.from(event.getOrganizer());
        this.eventTime = event.getEventTime();
        this.eventTypes = event.getTypes().stream()
                .map(t -> new EventTypeDto(t.getDescription(), t.getTranslation(), t.getGlobalId()))
                .collect(Collectors.toList());
        this.season = event.getSeason() == null ? null : SeasonDto.from(event.getSeason());
        this.kindOfDays = event.getKindOfDays();
        this.rooms = event.getRooms();
        this.usersNumber = event.getGuestsNumber();
        this.eventSize = event.getSize() == null ? null : new EventSizeDto(event.getSize().getDescription(), event.getSize().getGlobalId(), event.getSize().getImage() == null ? null : event.getSize().getImage().getGlobalId());
        this.nights = event.getNights();
        this.additionalRequirements = event.getAdditionalRequirements();
        this.budget = event.getBudget();
        this.estimatedCost = event.getEstimatedCost();
    }

}
