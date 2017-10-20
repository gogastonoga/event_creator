package com.capgemini.wolimierz.controller.dto;

import com.capgemini.wolimierz.form.HomePageSettings;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class HomePageDto extends DescriptionDto {

    private UUID backgroundVideoId;
    private UUID backgroundImageId;
    private String contactRequestFormat;

    public HomePageDto(String description, UUID backgroundImageId, UUID backgroundVideoId, String contactRequestFormat) {
        super(description);
        this.backgroundVideoId = backgroundVideoId;
        this.backgroundImageId = backgroundImageId;
        this.contactRequestFormat = contactRequestFormat;

    }

    public static HomePageDto from(HomePageSettings homePageSettings) {
        return new HomePageDto(homePageSettings.getDescription(),
                homePageSettings.getImage() == null ? null : homePageSettings.getImage().getGlobalId(),
                homePageSettings.getVideo() == null ? null : homePageSettings.getVideo().getGlobalId(),
                homePageSettings.getContactRequestFormat());
    }

}
