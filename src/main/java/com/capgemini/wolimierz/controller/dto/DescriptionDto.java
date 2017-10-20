package com.capgemini.wolimierz.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class DescriptionDto {
    @NotNull
    private String description;

    public DescriptionDto(String description) {
        this.description = description;
    }
}
