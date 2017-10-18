package com.capgemini.wolimierz.controller.dto;

import com.capgemini.wolimierz.event.dto.SeasonDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class SeasonsContainer {
    private List<SeasonDto> seasons = new ArrayList<>();
}
