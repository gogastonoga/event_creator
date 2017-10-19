package com.capgemini.wolimierz.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DescriptionDto {
    private String description;

    public DescriptionDto(String description) {
        this.description = description;
    }
}
