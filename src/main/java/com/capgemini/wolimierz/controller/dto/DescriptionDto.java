package com.capgemini.wolimierz.controller.dto;

import lombok.Getter;

@Getter
public class DescriptionDto {
    private String description;

    public DescriptionDto(String description) {
        this.description = description;
    }
}
