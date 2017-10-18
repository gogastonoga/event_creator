package com.capgemini.wolimierz.event.dto;


import com.capgemini.wolimierz.controller.dto.DescriptionDto;
import com.capgemini.wolimierz.event.model.EventSize;
import lombok.Getter;

import java.util.UUID;

@Getter
public class SizeDto extends DescriptionDto {
    private UUID globalId;
    private String bounds;
    private String imageUrl;

    public SizeDto(UUID globalId, int lowerBound, int higherBound, String description, String imageUrl) {
        super(description);
        this.bounds = String.format("%d - %d", lowerBound, higherBound);
        this.globalId = globalId;
        this.imageUrl = imageUrl;// + "http://icons.iconarchive.com/icons/fasticon/nature/256/Red-Flower-icon.png";
    }

    public static SizeDto from(EventSize size) {
        return new SizeDto(size.getGlobalId(), size.getLowerBound(), size.getHigherBound(), size.getDescription(),
                size.getImageUrl());
    }
}
