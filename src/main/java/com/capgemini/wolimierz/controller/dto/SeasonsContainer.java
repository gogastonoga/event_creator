package com.capgemini.wolimierz.controller.dto;

import com.capgemini.wolimierz.event.dto.SeasonDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SeasonsContainer {
    @NotNull
    private List<SeasonDto> seasons = new ArrayList<>();
}
