package com.capgemini.wolimierz.controller.dto;

import com.capgemini.wolimierz.event.model.KindOfDays;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventDto {
    @NotNull
    private OrganizerDto organizer;
    private LocalDateTime eventTime;
    private List<UUID> eventTypeIds = new ArrayList<>();
    @NotNull
    private UUID seasonId;
    @NotNull
    private KindOfDays kindOfDays;
    private int rooms;
    private int guestsNumber;
    @NotNull
    private UUID eventSizeId;
    @NotNull
    private int nights;
    private String additionalRequirements;
    @NotNull
    private double maxCost;
}
