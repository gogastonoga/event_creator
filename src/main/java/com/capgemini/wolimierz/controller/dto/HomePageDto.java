package com.capgemini.wolimierz.controller.dto;

import com.capgemini.wolimierz.form.HomePageSettings;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HomePageDto extends DescriptionDto {

    private String backgroundVideoUrl;
    private String backgroundImageUrl;
    private String contactRequestFormat;

    public HomePageDto(String description, String backgroundImageUrl, String backgroundVideoUrl, String contactRequestFormat) {
        super(description);
        this.backgroundVideoUrl = backgroundVideoUrl;
        this.backgroundImageUrl = backgroundImageUrl;
        this.contactRequestFormat = contactRequestFormat;

    }

    public static HomePageDto from(HomePageSettings homePageSettings, String mediaGetUrl) {
        return new HomePageDto(homePageSettings.getDescription(),
                homePageSettings.getImage() == null ? null : mediaGetUrl + homePageSettings.getImage().getGlobalId(),
                homePageSettings.getVideo() == null ? null : mediaGetUrl + homePageSettings.getVideo().getGlobalId(),
                homePageSettings.getContactRequestFormat());
    }

}
