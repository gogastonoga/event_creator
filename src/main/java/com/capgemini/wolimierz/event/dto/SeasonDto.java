package com.capgemini.wolimierz.event.dto;

import com.capgemini.wolimierz.event.model.Season;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class SeasonDto {
    private LocalDate from;
    private LocalDate to;
    private UUID globalId;
    private String name;

    public SeasonDto(LocalDate from, LocalDate to, String name, UUID globalId) {
        this.from = from;
        this.to = to;
        this.globalId = globalId;
        this.name = name;
    }

    public static SeasonDto from(Season season) {
        return new SeasonDto(season.getFromDate(), season.getToDate(), season.getName(), season.getGlobalId());
    }
}
