package com.capgemini.wolimierz.form;

import com.capgemini.wolimierz.event.dto.EventDto;
import com.capgemini.wolimierz.event.dto.SeasonDto;
import com.capgemini.wolimierz.event.dto.SizeDto;
import com.capgemini.wolimierz.event.model.EventSize;
import com.capgemini.wolimierz.event.model.EventType;
import com.capgemini.wolimierz.event.predefined.PredefinedEventType;
import com.capgemini.wolimierz.event.model.Season;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class FormDto {
    private List<EventDto> events;
    private List<SizeDto> sizes;
    private List<SeasonDto> seasons;
    private String budgetDescription;
    private String additionalDescription;
    private String participantsDescription;
    private String accommodationDescription;
    private String dateFormDescription;

    public static FormDto from(Form form, List<EventType> eventTypes, List<EventSize> sizes, List<Season> seasons) {
        return new FormDto(
                eventTypes.stream()
                        .map(EventDto::new)
                        .collect(Collectors.toList()),
                sizes.stream()
                        .map(SizeDto::from)
                        .collect(Collectors.toList()),
                seasons.stream()
                        .map(season -> new SeasonDto(season.getFromDate(), season.getToDate(), season.getDescription(),
                                season.getName(), season.getGlobalId()))
                        .collect(Collectors.toList()),
                form.getBudgetDescription(),
                form.getAdditionalDescription(),
                form.getParticipantsDescription(),
                form.getAccommodationDescription(),
                form.getDateFormDescription()
        );
    }
}
