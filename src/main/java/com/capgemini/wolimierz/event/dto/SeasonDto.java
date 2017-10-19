package com.capgemini.wolimierz.event.dto;

import com.capgemini.wolimierz.controller.dto.DescriptionDto;
import com.capgemini.wolimierz.event.model.Season;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class SeasonDto extends DescriptionDto {
    private LocalDate from;
    private LocalDate to;
    private UUID globalId;
    private String name;

    public SeasonDto(LocalDate from, LocalDate to, String description, String name, UUID globalId) {
        super(description);
        this.from = from;
        this.to = to;
        this.globalId = globalId;
        this.name = name;
    }

    public static SeasonDto from(Season season) {
        return new SeasonDto(season.getFromDate(), season.getToDate(), season.getDescription(), season.getName(), season.getGlobalId());
    }
}
