package com.capgemini.wolimierz.controller.dto;

import com.capgemini.wolimierz.event.model.KindOfDays;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventDto {
    private OrganizerDto organizer;
    private LocalDateTime eventTime;
    private List<UUID> eventTypeIds = new ArrayList<>();
    private UUID seasonId;
    private KindOfDays kindOfDays;
    private int rooms;
    private int guestsNumber;
    private UUID eventSizeId;
    private int nights;
    private String additionalRequirements;
    private double maxCost;
}
